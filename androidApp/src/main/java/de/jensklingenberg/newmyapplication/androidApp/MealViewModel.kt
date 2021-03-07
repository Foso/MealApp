package de.jensklingenberg.newmyapplication.androidApp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.jensklingenberg.newmyapplication.shared.MealDataSource
import de.jensklingenberg.newmyapplication.shared.models.Category
import de.jensklingenberg.newmyapplication.shared.models.Meal
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MealViewModel(private val mealDataSource: MealDataSource) : ViewModel() {

    val mealsState = mutableStateOf<List<Meal>>(emptyList())

    val categories: StateFlow<List<Category>> = getCategories()
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    fun getMeals() {
        GlobalScope.launch {
            mealsState.value=(mealDataSource.getMeals())
        }
    }

    private fun getCategories(): Flow<List<Category>> = flow {
        emit(mealDataSource.getCategories())
    }

    fun searchByCategory(name: String) {
        GlobalScope.launch {
            mealsState.value=(mealDataSource.getMealsByCategory(name))
        }
    }

    fun searchByName(name: String) {
        GlobalScope.launch {
            mealsState.value=(mealDataSource.getMealsByName(name))
        }
    }

    fun getMealImage(personName: String): String = getMeal(personName)?.strMealThumb ?: ""

    fun getMeal(personName: String): Meal? = mealsState.value.find { it.strMeal == personName }

    fun getIngredientImage(personName: String): String =
        mealDataSource.getIngredientImageUrl(personName)

}
