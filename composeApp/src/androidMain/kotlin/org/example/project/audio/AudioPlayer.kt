package org.example.project.audio

import android.media.MediaPlayer
import android.util.Log
import org.example.project.AppContextHolder
import org.example.project.R

actual class AudioPlayer actual constructor() {

    companion object {
        private var mediaPlayer: MediaPlayer? = null
        private var bgPlayer: MediaPlayer? = null
    }

    actual fun startBackgroundMusic() {
        if (bgPlayer?.isPlaying == true) return

        try {
            val context = AppContextHolder.context
            // Solo creamos una instancia nueva si bgPlayer es nulo
            if (bgPlayer == null) {
                bgPlayer = MediaPlayer.create(context, R.raw.the_stardust_man_appears)
                bgPlayer?.isLooping = true
            }
            bgPlayer?.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    actual fun stopBackgroundMusic() {
        bgPlayer?.let {
            if (it.isPlaying) it.stop()
            it.release()
        }
        bgPlayer = null
    }

    actual fun playSound() {
        val context = AppContextHolder.context
        mediaPlayer?.release()

        mediaPlayer = MediaPlayer.create(context, R.raw.the_stardust_man_appears)
        mediaPlayer?.start()
    }

    object AudioInstance {
        val player = AudioPlayer()
    }
}