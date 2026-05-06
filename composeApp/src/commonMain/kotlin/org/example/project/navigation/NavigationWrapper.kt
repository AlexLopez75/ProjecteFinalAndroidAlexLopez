package org.example.project.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import org.example.project.model.CardProvider
import org.example.project.screens.GameScreen
import org.example.project.screens.OptionsScreen
import org.example.project.screens.ResultScreen
import org.example.project.screens.TitleScreen
import org.example.project.viewmodel.GameViewModel

@Composable
fun NavigationWrapper(){
    val backStack = rememberNavBackStack(navConfig, Route.TitleScreen)
    val cardEntity = CardProvider
    val gameViewModel: GameViewModel = viewModel { GameViewModel() }
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Route.TitleScreen> {
                TitleScreen(
                    navigateTo2 = { backStack.add(Route.OptionsScreen) },
                    navigateTo3 = {
                        gameViewModel.randomizeOptions()
                        backStack.add(Route.GameScreen) }
                )
            }
            entry<Route.OptionsScreen> {
                OptionsScreen(
                    navigateBack = { backStack.removeLastOrNull() },
                    navigateTo3 = { backStack.add(Route.GameScreen) },
                    viewModel = gameViewModel
                )
            }
            entry<Route.GameScreen> { key ->
                GameScreen(
                    navigateTo1 = {
                        backStack.clear() //We do this to not consume infinite memory
                        backStack.add(Route.TitleScreen) },
                    navigateTo4 = { backStack.add(Route.ResultScreen) },
                    cardEntity = cardEntity,
                    viewModel = gameViewModel
                )
            }

            entry<Route.ResultScreen> {
                ResultScreen(
                    navigateTo1 = {
                        backStack.clear()
                        backStack.add(Route.TitleScreen) },
                    viewModel = gameViewModel
                    )
            }
        }
    )
}
