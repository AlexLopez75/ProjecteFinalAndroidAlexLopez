package org.example.project

import kotlin.test.BeforeTest

expect fun initializeTestContext()

abstract class BaseGuiTest {
    @BeforeTest
    fun setup() {
        initializeTestContext()
    }
}