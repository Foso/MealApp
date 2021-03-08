package de.jensklingenberg.newmyapplication.androidApp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.jensklingenberg.mealdbapi.Category
import de.jensklingenberg.network.model.Meal
import de.jensklingenberg.MealDataSource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MealViewModel(private val mealDataSource: MealDataSource) : ViewModel() {

    val mealsState = mutableStateOf<List<Meal>>(emptyList())

    val categories: StateFlow<List<Category>> = getCategories()
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    fun getMeals() {
        GlobalScope.launch {
            mealsState.value = (mealDataSource.getMeals())
        }
    }

    private fun getCategories(): Flow<List<Category>> = flow {
        emit(mealDataSource.getCategories())
    }

    fun searchByCategory(name: String) {
        GlobalScope.launch {
            mealsState.value = (mealDataSource.getMealsByCategory(name))
        }
    }

    fun searchByName(name: String) {
        GlobalScope.launch {
            mealsState.value = (mealDataSource.getMealsByName(name))
        }
    }

    fun getMealImage(mealName: String): String = getMeal(mealName)?.strMealThumb ?: ""

    fun getMeal(mealName: String): Meal? =
        mealsState.value.find { it.strMeal == mealName }

    fun getIngredientImage(ingredientName: String): String =
        mealDataSource.getIngredientImageUrl(ingredientName)

}
