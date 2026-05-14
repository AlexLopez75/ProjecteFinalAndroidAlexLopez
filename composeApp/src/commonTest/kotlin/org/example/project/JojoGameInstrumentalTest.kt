package org.example.project

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.runComposeUiTest
import org.example.project.audio.AudioPlayer
import org.example.project.model.CardProvider
import org.example.project.model.Difficulty
import org.example.project.screens.GameScreen
import org.example.project.screens.OptionsScreen
import org.example.project.screens.ResultScreen
import org.example.project.screens.TitleScreen
import org.example.project.viewmodel.GameViewModel
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class JojoGameInstrumentalTest : BaseTest(){

    // --- TITLE SCREEN TESTS ---

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun titleScreen_elementsVisibility() = runComposeUiTest {
        setContent {
            TitleScreen(audioPlayer = AudioPlayer(), navigateTo2 = {}, navigateTo3 = {})
        }

        onNodeWithTag("jojo_main_logo").assertIsDisplayed()

        // Hacemos scroll hasta el botón antes de verificar si se ve
        onNodeWithTag("btn_custom_game")
            .performScrollTo() // Esto es clave
            .assertIsDisplayed()

        onNodeWithTag("btn_quick_play")
            .performScrollTo()
            .assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun titleScreen_navigationTriggers() = runComposeUiTest {
        var navigatedToOptions = false
        setContent {
            TitleScreen(
                audioPlayer = AudioPlayer(),
                navigateTo2 = { navigatedToOptions = true },
                navigateTo3 = {})
        }

        onNodeWithTag("btn_custom_game").performClick()
        assertTrue(navigatedToOptions, "Debería haber activado la navegación a opciones")
    }

    // --- OPTIONS SCREEN TESTS ---

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun optionsScreen_difficultySelectionUpdate() = runComposeUiTest {
        val viewModel = GameViewModel()
        setContent {
            OptionsScreen(navigateBack = {}, navigateTo3 = {}, viewModel = viewModel)
        }

        onNodeWithTag("btn_difficulty_HARD").performClick()

        // Esperamos hasta que la condición sea cierta (máximo 5 segundos)
        waitUntil(
            timeoutMillis = 5000,
            condition = { viewModel.selectedDifficulty.value == Difficulty.HARD }
        )

        assertEquals(Difficulty.HARD, viewModel.selectedDifficulty.value)
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun optionsScreen_timerToggleUpdate() = runComposeUiTest {
        val viewModel = GameViewModel()
        viewModel.toggleTimer(true)

        setContent {
            OptionsScreen(navigateBack = {}, navigateTo3 = {}, viewModel = viewModel)
        }

        onNodeWithTag("timer_switch")
            .assertIsOn()
            .performClick() // Si falla, intenta: .performSemanticsAction(SemanticsActions.OnClick)

        waitForIdle()

        assertEquals(false, viewModel.isTimerEnabled.value)
    }

    // --- GAME SCREEN TESTS ---

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun gameScreen_layoutAndTimerVisibility() = runComposeUiTest {
        val viewModel = GameViewModel()
        viewModel.toggleTimer(true) // Aseguramos que el timer esté activo

        setContent {
            GameScreen(
                navigateTo1 = {},
                navigateTo4 = {},
                cardEntity = CardProvider,
                viewModel = viewModel
            )
        }

        onNodeWithTag("game_screen_container").assertIsDisplayed()
        onNodeWithTag("timer_progress_bar").assertIsDisplayed()
        onNodeWithTag("cards_grid").assertIsDisplayed()
        onNodeWithTag("btn_game_restart").assertIsDisplayed()
    }

    // --- RESULT SCREEN TESTS ---

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun resultScreen_displaysRankAndStars() = runComposeUiTest {
        val viewModel = GameViewModel()
        // No activamos el timer para simular el rango "N/A" (Training Complete)
        viewModel.toggleTimer(false)

        setContent {
            ResultScreen(navigateTo1 = {}, viewModel = viewModel)
        }

        onNodeWithTag("game_results").assertIsDisplayed()
        onNodeWithTag("rank").assertTextEquals("N/A")

        // Verificamos que se muestran las 3 estrellas (como iconos)
        onAllNodesWithTag("star_icon").assertCountEquals(3)

        onNodeWithTag("return_title").assertIsDisplayed()
    }
}