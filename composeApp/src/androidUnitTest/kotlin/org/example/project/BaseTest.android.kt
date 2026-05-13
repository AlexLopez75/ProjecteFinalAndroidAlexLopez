package org.example.project

import androidx.test.platform.app.InstrumentationRegistry
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
actual abstract class BaseTest

actual fun initializeTestContext() {
    try {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        AppContextHolder.init(instrumentation.targetContext)
    } catch (e: IllegalStateException) {
        // Si falla, es que estamos en un Unit Test normal o en otra plataforma simulada
        println("No se pudo inicializar la instrumentación: ${e.message}")
    }
}