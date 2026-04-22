package org.example.project.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.example.project.components.Card
import org.example.project.model.CardProvider
import org.example.project.viewmodel.GameViewModel

@Composable
fun GameScreen(
    navigateTo1: () -> Unit,
    navigateTo4: () -> Unit,
    cardEntity: CardProvider,
    viewModel: GameViewModel = viewModel()
) {
    val cardList by viewModel.cards.collectAsState()
    val timeLeft by viewModel.timeLeft.collectAsState()
    var useTimerToggle by remember { mutableStateOf(false) }

    val isGameFinished = cardList.isNotEmpty() && cardList.all { it.isMatched }
    val isGameOver = timeLeft == 0 && !isGameFinished

    LaunchedEffect(key1 = isGameFinished) {
        if (isGameFinished) {
            navigateTo4()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.setupGame()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        if (timeLeft != null) {
            val progress = viewModel.remainingTimeProgress

            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .padding(25.dp, 40.dp)
                    .height(25.dp)
                    .width(500.dp),
                color = if (progress < 0.3f) Color.Red else Color(0xFFFFD700),
                trackColor = Color.White,
                strokeCap = StrokeCap.Round
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(items = cardList, key = {it.uniqueId}) { memoryCard ->
                Card(
                    memoryCard = memoryCard,
                    onClick = { viewModel.onCardClicked(memoryCard)}
                )
            }
        }

        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = navigateTo1) { Text("Back") }
            Button(onClick = { viewModel.setupGame() }) { Text("Restart") }
        }
    }
}
