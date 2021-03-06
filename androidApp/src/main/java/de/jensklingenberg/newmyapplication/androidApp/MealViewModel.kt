package de.jensklingenberg.newmyapplication.androidApp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.jensklingenberg.newmyapplication.shared.PeopleInSpaceRepository
import de.jensklingenberg.newmyapplication.shared.ktor.MealApiImpl
import de.jensklingenberg.newmyapplication.shared.models.Meal
import de.jensklingenberg.newmyapplication.shared.models.MealResult
import kotlinx.coroutines.flow.*

class MealViewModel(val peopleInSpaceRepository: PeopleInSpaceRepository) : ViewModel() {
    private val cocktailApi = MealApiImpl()

    val peopleInSpace: StateFlow<List<Meal>> = flowgetMeals2()
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    fun flowgetMeals2(): Flow<List<Meal>> = flow {
        emit( cocktailApi.getCocktails().meals)
    }

    fun getMealImage(personName: String): String = getMeal(personName )?.strMealThumb ?: ""

    fun getMeal(personName: String): Meal? = peopleInSpace.value.find { it.strMeal == personName}
}
