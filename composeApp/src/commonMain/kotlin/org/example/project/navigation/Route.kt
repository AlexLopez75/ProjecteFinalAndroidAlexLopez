package org.example.project.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Route: NavKey {
    @Serializable
    data object TitleScreen : Route()
    @Serializable
    data object OptionsScreen : Route()
    @Serializable
    data class GameScreen(val userId: String) : Route()
    @Serializable
    data object ResultScreen : Route()
}
