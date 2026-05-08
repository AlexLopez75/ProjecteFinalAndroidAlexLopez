package org.example.project.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.viewmodel.GameViewModel

@Composable
fun ResultScreen(
    navigateTo1: () -> Unit,
    viewModel: GameViewModel
){
    val isTimerEnabled by viewModel.isTimerEnabled.collectAsState()
    val scorePercentage = if (isTimerEnabled){
        (viewModel.remainingTimeProgress * 100).toInt()
    } else {
        100
    }

    val (stars, rank, message, rankColor) = when {
        !isTimerEnabled -> listOf(3, "N/A", "TRAINING COMPLETE", Color.Gray)
        scorePercentage >= 70 -> listOf(3, "S", "DI MOLTO! PERFECT!", Color(0xFFFFD700))
        scorePercentage >= 40 -> listOf(2, "A", "GURETO DAZE!", Color(0xFF00BCD4))
        scorePercentage > 0 -> listOf(1, "B", "YARE YARE...", Color(0xFFE91E63))
        else -> listOf(0, "F", "GAME OVER", Color.Red)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "GAME RESULTS",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Black,
                fontStyle = FontStyle.Italic,
                color = Color.White
            ),
            modifier = Modifier.testTag("game_results")
        )

        Spacer(Modifier.height(32.dp))

        Text(
            text = "RANK",
            color = Color.Gray,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.testTag("rank_title")
        )
        Text(
            text = rank as String,
            style = MaterialTheme.typography.displayLarge.copy(
                fontWeight = FontWeight.Black,
                fontStyle = FontStyle.Italic,
                fontSize = 100.sp,
                color = rankColor as Color
            ),
            modifier = Modifier.padding(vertical = 8.dp).testTag("rank")
        )

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            for (i in 1..3) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Star",
                    modifier = Modifier.size(60.dp).testTag("star_icon"),
                    tint = if (i <= stars as Int) Color(0xFFFFD700) else Color.DarkGray
                )
            }
        }

        if (isTimerEnabled) {
            Text(
                text = "TIME REMAINING: $scorePercentage%",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                ),
                modifier = Modifier.testTag("time_title")
            )
        }

        Spacer(Modifier.height(8.dp))

        Text(
            text = message as String,
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Black,
                fontStyle = FontStyle.Italic,
                color = rankColor
            ),
            modifier = Modifier.padding(bottom = 48.dp).testTag("time_remaining")
        )

        Button(
            onClick = navigateTo1,
            modifier = Modifier
                .widthIn(max = 800.dp)
                .fillMaxWidth(0.7f)
                .height(70.dp)
                .testTag("return_title"),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD700)),
            shape = CutCornerShape(12.dp)
        ) {
            Text("RETURN TO TITLE",
                color = Color.Black,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Black),
            )
        }
    }
}