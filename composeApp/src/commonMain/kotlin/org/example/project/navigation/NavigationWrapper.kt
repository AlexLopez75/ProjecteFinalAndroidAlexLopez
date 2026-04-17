package org.example.project.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import org.example.project.model.CardProvider
import org.example.project.screens.GameScreen
import org.example.project.screens.OptionsScreen
import org.example.project.screens.ResultScreen
import org.example.project.screens.TitleScreen

@Composable
fun NavigationWrapper(){
    val backStack = rememberNavBackStack(navConfig, Route.TitleScreen)
    val cardEntity = CardProvider
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Route.TitleScreen> {
                TitleScreen(
                    navigateTo2 = { backStack.add(Route.OptionsScreen) },
                    navigateTo3 = { backStack.add(Route.GameScreen(userId = "user_42")) }
                )
            }
            entry<Route.OptionsScreen> {
                OptionsScreen(
                    navigateBack = { backStack.removeLastOrNull() },
                    navigateTo3 = { backStack.add(Route.GameScreen(userId = "user_42")) },
                )
            }
            entry<Route.GameScreen> { key ->
                GameScreen(
                    navigateTo1 = { backStack.add(Route.TitleScreen) },
                    navigateTo4 = { backStack.add(Route.ResultScreen) },
                    cardEntity = cardEntity
                )
            }

            entry<Route.ResultScreen> {
                ResultScreen(navigateTo1 = { backStack.add(Route.TitleScreen) })
            }
        }
    )
}
