package org.example.project.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.model.CardEntity
import org.example.project.model.MemoryCard
import org.jetbrains.compose.resources.painterResource

@Composable
fun Card(
    memoryCard: MemoryCard,
    onClick: () -> Unit
) {
    if (memoryCard.isMatched) {
        Spacer(modifier = Modifier
            .size(100.dp)
            .padding(8.dp)
        )
        return
    }

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .height(170.dp)
            .width(50.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            if (memoryCard.isFaceUp) {
                Image(
                    painter = painterResource(memoryCard.cardEntity.image),
                    contentDescription = "Card image",
                    modifier = Modifier.size(100.dp)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = memoryCard.cardEntity.name,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )
            } else {
                Text(
                    text = "?",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.displayLarge
                )
            }
        }
    }
}