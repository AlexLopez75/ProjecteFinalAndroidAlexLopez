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
import androidx.compose.ui.test.runComposeUiTest
import org.example.project.model.CardProvider
import org.example.project.model.Difficulty
import org.example.project.screens.GameScreen
import org.example.project.screens.OptionsScreen
import org.example.project.screens.ResultScreen
import org.example.project.screens.TitleScreen
import org.example.project.viewmodel.GameViewModel
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class JojoGameInstrumentalTest : BaseGuiTest(){

    // --- TITLE SCREEN TESTS ---

    @Test
    fun titleScreen_elementsVisibility() = runComposeUiTest {
        setContent {
            TitleScreen(navigateTo2 = {}, navigateTo3 = {})
        }

        // Verificamos que el contenedor y los logos principales se muestran
        onNodeWithTag("title_screen_container").assertIsDisplayed()
        onNodeWithTag("jojo_main_logo").assertIsDisplayed()
        onNodeWithTag("bizarre_memory_logo").assertIsDisplayed()
        onNodeWithTag("jojo_stands").assertIsDisplayed()

        // Verificamos botones por Tag
        onNodeWithTag("btn_custom_game").assertIsDisplayed()
        onNodeWithTag("btn_quick_play").assertExists()
    }

    @Test
    fun titleScreen_navigationTriggers() = runComposeUiTest {
        var navigatedToOptions = false
        setContent {
            TitleScreen(navigateTo2 = { navigatedToOptions = true }, navigateTo3 = {})
        }

        onNodeWithTag("btn_custom_game").performClick()
        assertTrue(navigatedToOptions, "Debería haber activado la navegación a opciones")
    }

    // --- OPTIONS SCREEN TESTS ---

    @Test
    fun optionsScreen_difficultySelectionUpdate() = runComposeUiTest {
        val viewModel = GameViewModel()
        setContent {
            OptionsScreen(navigateBack = {}, navigateTo3 = {}, viewModel = viewModel)
        }

        // Verificamos título
        onNodeWithTag("options_title").assertIsDisplayed()

        // Cambiamos a dificultad HARD usando el tag dinámico
        onNodeWithTag("btn_difficulty_HARD").performClick()

        // Verificación in-depth: El estado del ViewModel debe haber cambiado
        assertEquals(Difficulty.HARD, viewModel.selectedDifficulty.value)
    }

    @Test
    fun optionsScreen_timerToggleUpdate() = runComposeUiTest {
        val viewModel = GameViewModel()
        setContent {
            OptionsScreen(navigateBack = {}, navigateTo3 = {}, viewModel = viewModel)
        }

        // El switch debería estar activo por defecto (true)
        onNodeWithTag("timer_switch").assertIsOn()

        // Lo apagamos
        onNodeWithTag("timer_switch").performClick()

        // Verificamos estado
        onNodeWithTag("timer_switch").assertIsOff()
        assertEquals(false, viewModel.isTimerEnabled.value)
    }

    // --- GAME SCREEN TESTS ---

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