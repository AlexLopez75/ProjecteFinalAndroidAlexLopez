package org.example.project.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.model.CardProvider
import org.example.project.model.DeckType
import org.example.project.model.Difficulty
import org.example.project.model.MemoryCard

class GameViewModel: ViewModel() {
    private val _selectedDeck = MutableStateFlow(DeckType.PART_3)
    val selectedDeck: StateFlow<DeckType> = _selectedDeck.asStateFlow()

    private val _cards = MutableStateFlow<List<MemoryCard>>(emptyList())
    val cards: StateFlow<List<MemoryCard>> = _cards.asStateFlow()

    private val _timeLeft = MutableStateFlow<Int?>(null)
    val timeLeft: StateFlow<Int?> = _timeLeft.asStateFlow()

    private var totalTimeForProgress: Int = 1
    private val _selectedDifficulty = MutableStateFlow(Difficulty.NORMAL)
    val selectedDifficulty: StateFlow<Difficulty> = _selectedDifficulty.asStateFlow()

    private val _isTimerEnabled = MutableStateFlow(true)
    val isTimerEnabled: StateFlow<Boolean> = _isTimerEnabled.asStateFlow()

    val remainingTimeProgress: Float
        get(){
            val current = _timeLeft.value?.toFloat() ?: 1f
            return (current/totalTimeForProgress.toFloat()).coerceIn(0f, 1f)
        }

    private var firstSelectedCard: MemoryCard? = null
    private var isProcessing = false
    private var timerJob: Job? = null

    fun setupGame() {
        val difficulty = _selectedDifficulty.value
        val allCards = when (_selectedDeck.value) {
            DeckType.PART_3 -> CardProvider.ObtainDeckPart3()
            DeckType.PART_4 -> CardProvider.ObtainDeckPart4()
            DeckType.PART_5 -> CardProvider.ObtainDeckPart5()
            DeckType.PART_6 -> CardProvider.ObtainDeckPart6()
        }
        val selectedIds = allCards.map { it.id }.distinct().shuffled().take(difficulty.pairs)

        _cards.value = allCards.filter { it.id in selectedIds }
            .map { MemoryCard(cardEntity = it) }
            .shuffled()

        firstSelectedCard = null
        isProcessing = false

        if (_isTimerEnabled.value) {
            totalTimeForProgress = difficulty.timeSeconds
            startTimer(difficulty.timeSeconds)
        } else {
            _timeLeft.value = null
        }
    }

    private fun startTimer(seconds: Int?){
        timerJob?.cancel()
        _timeLeft.value = seconds

        if (seconds == null) return

        timerJob = viewModelScope.launch {
            while (_timeLeft.value != null && _timeLeft.value!! > 0) {
                delay(1000L)
                _timeLeft.value = _timeLeft.value!! - 1

                if (_cards.value.isNotEmpty() && _cards.value.all { it.isMatched }) {
                    break
                }
            }
            if (_timeLeft.value == 0) isProcessing = true
        }
    }

    fun onCardClicked(clickedCard: MemoryCard){
        if (isProcessing || clickedCard.isFaceUp || clickedCard.isMatched || _timeLeft.value == 0) return //Returns if processing, visible or matched card or timer = 0

        _cards.value = _cards.value.map {
            if (it.uniqueId == clickedCard.uniqueId) it.copy(isFaceUp = true) else it
        }

        if (firstSelectedCard == null){
            firstSelectedCard = clickedCard.copy(isFaceUp = true)
        } else {
            isProcessing = true
            val secondCard = clickedCard

            viewModelScope.launch {
                delay(1000)

                if (firstSelectedCard?.cardEntity?.id == secondCard.cardEntity.id) {
                    _cards.value = _cards.value.map {
                        if (it.cardEntity.id == secondCard.cardEntity.id) {
                            it.copy(isMatched = true)
                        } else it
                    }
                } else {
                    _cards.value = _cards.value.map {
                        if (it.uniqueId == firstSelectedCard?.uniqueId || it.uniqueId == secondCard.uniqueId) {
                            it.copy(isFaceUp = false)
                        } else it
                    }
                }

                firstSelectedCard = null
                isProcessing = false
            }
        }
    }

    fun updateDeck(deck: DeckType) {
        _selectedDeck.value = deck
    }

    fun updateDifficulty(difficulty: Difficulty) {
        _selectedDifficulty.value = difficulty
    }

    fun toggleTimer(enabled: Boolean) {
        _isTimerEnabled.value = enabled
    }
}