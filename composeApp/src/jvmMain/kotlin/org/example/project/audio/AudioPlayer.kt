package org.example.project.audio

import javazoom.jl.player.Player
import kotlin.concurrent.thread

actual class AudioPlayer actual constructor() {
    private var player: Player? = null
    private var isPlayingBg = false

    actual fun playSound() {
        thread(start = true) {
            try {
                val inputStream = AudioPlayer::class.java.getResourceAsStream("/the_stardust_man_appears.mp3")
                Player(inputStream).play()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    actual fun startBackgroundMusic() {
        if (isPlayingBg) return // Evita que suene dos veces si se llama por error
        isPlayingBg = true

        thread(start = true, name = "BackgroundMusicThread") {
            while (isPlayingBg) {
                try {
                    val inputStream = AudioPlayer::class.java.getResourceAsStream("/the_stardust_man_appears.mp3")
                    player = Player(inputStream)
                    player?.play() // Esta línea bloquea el hilo hasta que termina la canción
                } catch (e: Exception) {
                    println("Error en la música de fondo: ${e.message}")
                    isPlayingBg = false
                }
            }
        }
    }

    actual fun stopBackgroundMusic() {
        isPlayingBg = false
        player?.close() // Al cerrar el player, el hilo del bucle se libera
        player = null
    }
}