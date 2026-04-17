package org.example.project.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    val isGameFinished = cardList.isNotEmpty() && cardList.all { it.isMatched }

    LaunchedEffect(key1 = isGameFinished) {
        if (isGameFinished) {
            navigateTo4()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
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
