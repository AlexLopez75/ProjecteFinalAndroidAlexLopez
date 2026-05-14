package org.example.project.audio

import android.media.MediaPlayer
import android.util.Log
import org.example.project.AppContextHolder
import org.example.project.R

actual class AudioPlayer actual constructor() {
    private var mediaPlayer: MediaPlayer? = null
    private var bgPlayer: MediaPlayer? = null

    actual fun startBackgroundMusic() {
        if (bgPlayer?.isPlaying == true) return

        try {
            val context = AppContextHolder.context
            bgPlayer?.release()

            bgPlayer = MediaPlayer.create(context, org.example.project.R.raw.the_stardust_man_appears)

            bgPlayer?.apply {
                isLooping = true
                setVolume(1.0f, 1.0f)
                start()
            }
            Log.d("AUDIO_DEBUG","Música iniciada correctamente")
        } catch (e: Exception) {
            Log.d("AUDIO_DEBUG", "Error al reproducir: ${e.message}")
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
}