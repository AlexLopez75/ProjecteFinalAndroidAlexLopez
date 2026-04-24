package org.example.project.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
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
import org.example.project.components.Card
import org.example.project.model.DeckOptionCard
import org.example.project.model.DeckType
import org.example.project.model.Difficulty
import org.example.project.viewmodel.GameViewModel

@Composable
fun OptionsScreen(
    navigateBack: () -> Unit,
    navigateTo3: () -> Unit,
    viewModel: GameViewModel
) {
    val selectedDifficulty by viewModel.selectedDifficulty.collectAsState()
    val isTimerEnabled by viewModel.isTimerEnabled.collectAsState()
    val selectedDeck by viewModel.selectedDeck.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(24.dp))

        Text(
            "GAME OPTIONS",
            style = MaterialTheme.typography.headlineMedium.copy(
                color = Color.White,
                fontWeight = FontWeight.Black,
                fontStyle = FontStyle.Italic
            )
        )

        Spacer(Modifier.height(16.dp))

        Text("SELECT DECK", color = Color.Gray, style = MaterialTheme.typography.labelLarge)

        Box() {
            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                items(DeckType.entries.toTypedArray()) { deck ->
                    DeckOptionCard(
                        deck = deck,
                        isSelected = deck == selectedDeck,
                        onSelect = { viewModel.updateDeck(deck) }
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        Text("DIFFICULTY", color = Color.Gray, style = MaterialTheme.typography.labelLarge)

        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Difficulty.entries.forEach { difficulty ->
                val isSelected = selectedDifficulty == difficulty
                Button(
                    onClick = { viewModel.updateDifficulty(difficulty) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isSelected) Color(0xFFFFD700) else Color.DarkGray,
                        contentColor = if (isSelected) Color.Black else Color.White
                    ),
                    modifier = Modifier.weight(1f),
                    shape = CutCornerShape(8.dp)
                ) {
                    Text(difficulty.name, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(Modifier.height(8.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.DarkGray),
            shape = CutCornerShape(8.dp)
        ) {
            Row(
                modifier = Modifier.padding(12.dp).fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("ENABLE TIMER", color = Color.White, fontWeight = FontWeight.Bold)
                Switch(
                    checked = isTimerEnabled,
                    onCheckedChange = { viewModel.toggleTimer(it) }
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.setupGame()
                navigateTo3()
            },
            modifier = Modifier.fillMaxWidth().height(40.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD700)),
            shape = CutCornerShape(12.dp)
        ) {
            Text("YES! I AM! (PLAY)", color = Color.Black, fontWeight = FontWeight.Black)
        }

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                navigateBack()
            },
            modifier = Modifier.fillMaxWidth().height(40.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD700)),
            shape = CutCornerShape(12.dp)
        ) {
            Text("BACK", color = Color.Black, fontWeight = FontWeight.Black)
        }
    }
}
