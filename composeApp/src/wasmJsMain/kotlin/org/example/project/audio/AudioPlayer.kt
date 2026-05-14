package org.example.project.audio

import kotlinx.browser.document
import org.w3c.dom.HTMLAudioElement

actual class AudioPlayer actual constructor() {

    companion object {
        private var bgAudio: HTMLAudioElement? = null
    }

    actual fun startBackgroundMusic() {
        // Comprobamos el estado global
        if (bgAudio != null && !bgAudio!!.paused) return

        if (bgAudio == null) {
            bgAudio = document.createElement("audio") as HTMLAudioElement
            bgAudio?.src = "the_stardust_man_appears.mp3"
            bgAudio?.loop = true
        }

        bgAudio?.play()
    }

    actual fun stopBackgroundMusic() {
        bgAudio?.pause()
        bgAudio?.currentTime = 0.0
    }

    actual fun playSound() {
        val sfx = document.createElement("audio") as HTMLAudioElement
        sfx.src = "the_stardust_man_appears.mp3"
        sfx.play()
    }
}

