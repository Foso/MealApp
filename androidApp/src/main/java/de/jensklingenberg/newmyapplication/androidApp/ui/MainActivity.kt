package de.jensklingenberg.newmyapplication.androidApp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import de.jensklingenberg.newmyapplication.androidApp.ui.main.MainLayout
import de.jensklingenberg.newmyapplication.androidApp.MealViewModel
import de.jensklingenberg.newmyapplication.shared.MealRepository

val mealViewModel = MealViewModel(MealRepository())

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainLayout()
        }
    }
}


sealed class Screen(val title: String) {
    object MealListScreen : Screen("MealList")
    object MealDetailsDetails : Screen("MealDetails")
}





