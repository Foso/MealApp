package de.jensklingenberg.newmyapplication.shared

import de.jensklingenberg.newmyapplication.shared.models.Greeting
import kotlin.test.Test
import kotlin.test.assertTrue

class IosGreetingTest {

    @Test
    fun testExample() {
        assertTrue(Greeting().greeting().contains("iOS"), "Check iOS is mentioned")
    }
}