package org.example.project.navigation

import androidx.navigation3.runtime.NavKey
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

val navConfig = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(Route.TitleScreen::class, Route.TitleScreen.serializer())
            subclass(Route.OptionsScreen::class, Route.OptionsScreen.serializer())
            subclass(Route.GameScreen::class, Route.GameScreen.serializer())
            subclass(Route.ResultScreen::class, Route.ResultScreen.serializer())
        }
    }
}
