package org.example.project.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.model.CardProvider
import org.example.project.model.MemoryCard

class GameViewModel: ViewModel() {
    private val _cards = MutableStateFlow<List<MemoryCard>>(emptyList())
    val cards: StateFlow<List<MemoryCard>> = _cards.asStateFlow()

    private var firstSelectedCard: MemoryCard? = null
    private var isProcessing = false

    init {
        setupGame()
    }

    fun setupGame() {
        val allCards = CardProvider.ObtainCards()

        val availableIds = allCards.map { it.id }.distinct()
        val selectedIds = availableIds.shuffled().take(10)

        val deck = allCards.filter { it.id in selectedIds }.map { entity ->
            MemoryCard(cardEntity = entity)
        }.shuffled()

        _cards.value = deck
        firstSelectedCard = null
        isProcessing = false
    }

    fun onCardClicked(clickedCard: MemoryCard){
        if (isProcessing || clickedCard.isFaceUp || clickedCard.isMatched) return //Ignore if processing, visible or matched card

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
}