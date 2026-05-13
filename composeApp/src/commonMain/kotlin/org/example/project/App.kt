package org.example.project

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import org.example.project.navigation.NavigationWrapper
import org.example.project.audio.AudioPlayer

@Composable
@Preview
fun App() {
    val audioPlayer = remember { AudioPlayer() }

    LaunchedEffect(Unit) {
        audioPlayer.startBackgroundMusic()
    }

    MaterialTheme {
        NavigationWrapper()
    }
}