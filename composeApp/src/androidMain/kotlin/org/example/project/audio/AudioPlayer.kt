package org.example.project.audio

import android.media.MediaPlayer
import org.example.project.AppContextHolder
import org.example.project.R

actual class AudioPlayer actual constructor() {
    private var mediaPlayer: MediaPlayer? = null
    private var bgPlayer: MediaPlayer? = null

    actual fun startBackgroundMusic() {
        val context = AppContextHolder.context
        bgPlayer = MediaPlayer.create(context, R.raw.the_stardust_man_appears)
        bgPlayer?.isLooping = true // ¡ESTO ES CLAVE!
        bgPlayer?.start()
    }

    actual fun stopBackgroundMusic() {
        bgPlayer?.stop()
        bgPlayer?.release()
        bgPlayer = null
    }

    actual fun playSound() {
        val context = AppContextHolder.context
        mediaPlayer?.release()

        mediaPlayer = MediaPlayer.create(context, R.raw.the_stardust_man_appears)
        mediaPlayer?.start()
    }
}