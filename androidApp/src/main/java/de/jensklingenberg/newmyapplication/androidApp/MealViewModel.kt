package de.jensklingenberg.newmyapplication.androidApp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.jensklingenberg.newmyapplication.shared.MealDataSource
import de.jensklingenberg.newmyapplication.shared.models.Meal
import kotlinx.coroutines.flow.*

class MealViewModel(private val mealDataSource: MealDataSource) : ViewModel() {

    val peopleInSpace: StateFlow<List<Meal>> = getMeals()
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    private fun getMeals(): Flow<List<Meal>> = flow {
        emit(mealDataSource.getMeals().meals)
    }

    fun getMealImage(personName: String): String = getMeal(personName)?.strMealThumb ?: ""

    fun getMeal(personName: String): Meal? = peopleInSpace.value.find { it.strMeal == personName }

    fun getIngredientImage(personName: String): String = mealDataSource.getIngredientImageUrl(personName)

}
