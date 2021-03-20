package android


import androidx.compose.ui.test.junit4.createAndroidComposeRule
import de.jensklingenberg.newmyapplication.androidApp.ui.MainActivity
import de.jensklingenberg.test.OverviewTest
import de.jensklingenberg.test.SharedOverviewTest
import org.junit.Rule
import org.junit.Test


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleInstrumentedTest   {
    @Rule
    @JvmField
    var composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun whenUserClicksOnListItem_OpenDetailPage()  {
        OverviewTest(ComposeAndroidEnv(composeTestRule)).whenUserClicksOnListItem_OpenDetailPage()
    }


}

