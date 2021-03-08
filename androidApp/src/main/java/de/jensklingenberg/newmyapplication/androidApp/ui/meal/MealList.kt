package de.jensklingenberg.newmyapplication.androidApp.ui.meal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.TextFieldValue
import de.jensklingenberg.newmyapplication.androidApp.ui.category.CategoryBar
import de.jensklingenberg.newmyapplication.androidApp.ui.mealViewModel
import de.jensklingenberg.network.model.Meal

@Composable
fun MealList(mealSelected: (meal: Meal) -> Unit) {
    val peopleState = mealViewModel.mealsState
    val categoryState = mealViewModel.categories.collectAsState()
    val searchInput = remember { mutableStateOf(TextFieldValue()) }

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
            TextField(value = searchInput.value, onValueChange = {
                searchInput.value=it
                if(it.text.isNotBlank()){
                    mealViewModel.searchByName(it.text)
                }else{
                    mealViewModel.getMeals()
                }
            })
            LazyColumn {
                items(peopleState.value) { person ->
                    val mealImageUrl = mealViewModel.getMealImage(person.strMeal)
                    MealView(mealImageUrl, person, mealSelected)
                }
            }
        }
    }
}