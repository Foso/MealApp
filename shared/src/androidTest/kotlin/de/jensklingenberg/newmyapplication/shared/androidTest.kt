package de.jensklingenberg.newmyapplication.shared

import de.jensklingenberg.network.model.Greeting
import org.junit.Assert.assertTrue
import org.junit.Test

class AndroidGreetingTest {

    @Test
    fun testExample() {
        assertTrue("Check Android is mentioned", Greeting().greeting().contains("Android"))
    }
}