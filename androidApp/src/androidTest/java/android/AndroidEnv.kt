package android

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.rules.ActivityScenarioRule
import de.jensklingenberg.newmyapplication.androidApp.ui.MainActivity
import de.jensklingenberg.newmyapplication.androidApp.ui.main.MainLayout
import de.jensklingenberg.test.TestEnvironment
import org.junit.Assert

class ComposeAndroidEnv(val composeTestRule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>) : TestEnvironment {

    override fun launchApp() {
        composeTestRule.setContent {
            MainLayout()
        }
        Thread.sleep(3000)
    }

    override fun assertTrue(assert: Boolean) {
            Assert.assertTrue(assert)
    }

    override fun assertEquals(expected: String, actual: String) {
         Assert.assertEquals(expected, actual)
    }

    override fun clickOnNodeWithText(text: String) {
        composeTestRule.onNodeWithText(text).performClick()
    }

    override fun assertNodeDisplayed(text: String) {
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }
}