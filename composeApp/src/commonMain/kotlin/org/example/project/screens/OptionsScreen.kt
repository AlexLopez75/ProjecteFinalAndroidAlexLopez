package org.example.project.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.example.project.components.Card
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

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Game Options",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(24.dp))

        Text("Difficulty", style = MaterialTheme.typography.bodyMedium)

        Spacer(Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Difficulty.entries.forEach { difficulty ->
                val isSelected = selectedDifficulty == difficulty
                Button(
                    onClick = { viewModel.updateDifficulty(difficulty) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isSelected) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.surfaceVariant,
                        contentColor = if (isSelected) Color.White
                        else MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(difficulty.name)
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Row(
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("Timer", fontWeight = FontWeight.Bold)
                    Text(
                        "Enable timer?",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Switch(
                    checked = isTimerEnabled,
                    onCheckedChange = { viewModel.toggleTimer(it) }
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        Button(onClick = navigateTo3) { Text("Play") }

        Button(onClick = navigateBack) { Text("Volver") }
    }
}
