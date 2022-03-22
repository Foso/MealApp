package de.jensklingenberg.newmyapplication.androidApp.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import de.jensklingenberg.newmyapplication.androidApp.ui.MealAppTheme
import de.jensklingenberg.newmyapplication.androidApp.ui.meal.MealList
import de.jensklingenberg.newmyapplication.androidApp.ui.Screen
import de.jensklingenberg.newmyapplication.androidApp.ui.meal.MealDetailsView

@Composable
fun MainLayout() {
    val navController = rememberNavController()

    MealAppTheme {
        NavHost(navController, startDestination = Screen.MealListScreen.title) {
            composable(Screen.MealListScreen.title) {
                MealList(
                    mealSelected = {
                        navController.navigate(Screen.MealDetailsDetails.title + "/${it.strMeal}")
                    }
                )
            }
            composable(Screen.MealDetailsDetails.title + "/{meal}") { backStackEntry ->
                MealDetailsView(
                    backStackEntry.arguments?.get("meal") as String,
                    popBack = { navController.popBackStack() })
            }
        }
    }
}