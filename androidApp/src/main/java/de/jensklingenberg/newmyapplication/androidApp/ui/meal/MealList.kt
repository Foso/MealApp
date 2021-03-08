package de.jensklingenberg.newmyapplication.androidApp.ui.meal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import de.jensklingenberg.newmyapplication.androidApp.ui.category.CategoryBar
import de.jensklingenberg.newmyapplication.androidApp.ui.mealViewModel
import de.jensklingenberg.network.model.Meal

@Composable
fun MealList(mealSelected: (meal: Meal) -> Unit) {
    val mealsState = mealViewModel.mealsState
    val categoryState = mealViewModel.categories.collectAsState()

    SideEffect {
        mealViewModel.getMeals()
    }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Meal App") })
        }) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CategoryBar(categoryState.value, onClick = {
                mealViewModel.searchByCategory(it.strCategory)
            })
            SearchInput(){
                if(it.isNotBlank()){
                    mealViewModel.searchByName(it)
                }else{
                mealViewModel.getMeals()
            }
            }

            LazyColumn {
                items(mealsState.value) { meal ->
                    val mealImageUrl = mealViewModel.getMealImage(meal.strMeal)
                    MealView(mealImageUrl, meal, mealSelected)
                }
            }
        }
    }
}