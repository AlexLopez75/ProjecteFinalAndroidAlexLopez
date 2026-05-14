package org.example.project.audio

import javazoom.jl.player.Player
import java.io.BufferedInputStream
import java.io.InputStream
import kotlin.concurrent.thread

actual class AudioPlayer actual constructor() {

    companion object {
        private var bgPlayer: Player? = null
        private var isPlaying = false
        private var bgThread: Thread? = null
    }

    actual fun startBackgroundMusic() {
        // Cláusula de guarda: si ya está sonando, no hacemos nada
        if (isPlaying) return

        bgThread = thread(start = true, isDaemon = true) {
            try {
                isPlaying = true
                while (isPlaying) {
                    val inputStream: InputStream? = AudioPlayer::class.java.getResourceAsStream("/the_stardust_man_appears.mp3")
                        ?: break
                    val bis = BufferedInputStream(inputStream)
                    bgPlayer = Player(bis)
                    bgPlayer?.play()
                    // JLayer no tiene loop nativo, por eso usamos el while
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isPlaying = false
            }
        }
    }

    actual fun stopBackgroundMusic() {
        isPlaying = false
        bgPlayer?.close()
        bgThread?.interrupt()
        bgPlayer = null
    }

    actual fun playSound() {
        thread(start = true) {
            try {
                val inputStream: InputStream = Any().javaClass.getResourceAsStream("/the_stardust_man_appears.mp3")
                val bis = BufferedInputStream(inputStream)
                Player(bis).play()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}