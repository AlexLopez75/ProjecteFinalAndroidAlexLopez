package org.example.project.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.example.project.model.DeckOptionCard
import org.example.project.model.DeckType
import org.example.project.viewmodel.GameViewModel

@Composable
fun DeckSelectionScreen(
    viewModel: GameViewModel,
    onStartGame: () -> Unit
) {
    val selectedDeck by viewModel.selectedDeck.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212)) // Fondo oscuro para resaltar los colores
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "CHOOSE YOUR DESTINY",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Black,
                fontStyle = FontStyle.Italic,
                color = Color.White
            ),
            modifier = Modifier.padding(bottom = 24.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(1), // Puedes cambiar a 2 si quieres cartas más pequeñas
            modifier = Modifier.weight(1f)
        ) {
            items(DeckType.entries.toTypedArray()) { deck ->
                DeckOptionCard(
                    deck = deck,
                    isSelected = deck == selectedDeck,
                    onSelect = { viewModel.updateDeck(deck) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón con estilo JoJo
        Button(
            onClick = {
                viewModel.setupGame()
                onStartGame()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD700)), // Dorado
            shape = CutCornerShape(12.dp) // Esquinas cortadas para un look más agresivo
        ) {
            Text(
                text = "YES! I AM!",
                color = Color.Black,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Black)
            )
        }
    }
}