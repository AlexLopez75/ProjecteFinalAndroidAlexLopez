package org.example.project

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.example.project.model.DeckType
import org.example.project.model.Difficulty
import org.example.project.viewmodel.GameViewModel
import kotlin.test.Test
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class UnitTest {
    private lateinit var viewModel: GameViewModel
    private val testDispatcher = UnconfinedTestDispatcher()

    @BeforeTest
    fun setup() {
        // Redirigimos el Dispatcher principal al de test
        Dispatchers.setMain(testDispatcher)
        viewModel = GameViewModel()
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `setupGame inicializa correctamente la lista de cartas segun dificultad`() = runTest {
        // Configuramos dificultad Normal (debería tener X parejas según tu modelo)
        viewModel.updateDifficulty(Difficulty.NORMAL)
        viewModel.setupGame()

        val cards = viewModel.cards.value
        assertNotNull(cards)
        // Verificamos que la cantidad de cartas sea el doble de parejas de la dificultad Normal
        assertEquals(Difficulty.NORMAL.pairs * 2, cards.size)
        // Verificamos que todas las cartas empiecen boca abajo
        assertTrue(cards.all { !it.isFaceUp })
    }

    @Test
    fun `updateDeck actualiza el estado correctamente`() = runTest {
        viewModel.updateDeck(DeckType.PART_5)
        assertEquals(DeckType.PART_5, viewModel.selectedDeck.value)
    }

    @Test
    fun `onCardClicked voltea una carta correctamente`() = runTest {
        viewModel.setupGame()
        val firstCard = viewModel.cards.value.first()

        viewModel.onCardClicked(firstCard)

        // Verificamos que la carta específica ahora esté boca arriba
        assertTrue(viewModel.cards.value.first { it.uniqueId == firstCard.uniqueId }.isFaceUp)
    }

    @Test
    fun `cuando dos cartas no coinciden se vuelven a voltear tras un delay`() = runTest {
        // Usamos el dispatcher de este runTest para controlar el tiempo con precisión
        viewModel.setupGame()

        val cards = viewModel.cards.value
        val firstCard = cards[0]
        val differentCard = cards.first { it.cardEntity.id != firstCard.cardEntity.id }

        // Acto 1: Hacemos click en las dos cartas
        viewModel.onCardClicked(firstCard)
        viewModel.onCardClicked(differentCard)

        // Acto 2: Avanzamos un poco de tiempo, pero MENOS de los 1000ms del delay
        // Esto obliga a las corrutinas a ejecutarse hasta encontrar el delay
        testScheduler.advanceTimeBy(500)

        // AHORA SÍ: En este punto, el delay de 1000ms sigue activo
        assertTrue(viewModel.cards.value.first { it.uniqueId == firstCard.uniqueId }.isFaceUp, "La primera carta debería seguir boca arriba")
        assertTrue(viewModel.cards.value.first { it.uniqueId == differentCard.uniqueId }.isFaceUp, "La segunda carta debería seguir boca arriba")

        // Acto 3: Avanzamos el resto del tiempo (otros 600ms para asegurar)
        testScheduler.advanceTimeBy(600)

        // FINAL: El delay terminó, las cartas deben haberse volteado
        assertFalse(viewModel.cards.value.first { it.uniqueId == firstCard.uniqueId }.isFaceUp, "La primera carta debería haberse ocultado")
        assertFalse(viewModel.cards.value.first { it.uniqueId == differentCard.uniqueId }.isFaceUp, "La segunda carta debería haberse ocultado")
    }

    @Test
    fun `randomizeOptions cambia los valores por defecto`() = runTest {
        // Este test es probabilístico, pero sirve para verificar que la función ejecuta cambios
        val initialDeck = viewModel.selectedDeck.value
        var wasChanged = false

        // Ejecutamos varias veces para asegurar que cambie algo
        repeat(10) {
            viewModel.randomizeOptions()
            if (viewModel.selectedDeck.value != initialDeck) {
                wasChanged = true
            }
        }

        // Es altamente improbable que tras 5 randoms siga igual
        // (Aunque técnicamente podría pasar, sirve para testear la ejecución)
        assertTrue(wasChanged, "El mazo debería haber cambiado al menos una vez tras 10 intentos")
    }

    @Test
    fun `el temporizador se inicializa correctamente si esta activado`() = runTest {
        viewModel.toggleTimer(true)
        viewModel.updateDifficulty(Difficulty.NORMAL)
        viewModel.setupGame()

        assertEquals(Difficulty.NORMAL.timeSeconds, viewModel.timeLeft.value)
    }@Test
    fun example() {
        assertEquals(3, 1 + 2)
    }
}