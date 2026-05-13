package org.example.project.audio

import kotlinx.browser.document
import org.w3c.dom.HTMLAudioElement

actual class AudioPlayer actual constructor() {
    private var bgAudio: HTMLAudioElement? = null

    actual fun playSound() {
        val audio = document.createElement("audio") as HTMLAudioElement
        audio.src = "the_stardust_man_appears.mp3"
        audio.play()
    }

    actual fun startBackgroundMusic() {
        if (bgAudio == null) {
            bgAudio = document.createElement("audio") as HTMLAudioElement
        }
        bgAudio?.let {
            it.src = "the_stardust_man_appears.mp3"
            it.loop = true // Indica al navegador que lo repita
            it.play()
        }
    }

    actual fun stopBackgroundMusic() {
        bgAudio?.pause()
        bgAudio?.currentTime = 0.0 // Reinicia la canción
    }
}