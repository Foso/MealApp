package de.jensklingenberg.newmyapplication.androidApp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.jensklingenberg.newmyapplication.shared.PeopleInSpaceRepository
import de.jensklingenberg.newmyapplication.shared.ktor.CocktailApiImpl
import de.jensklingenberg.newmyapplication.shared.models.CocktailResult
import de.jensklingenberg.newmyapplication.shared.models.Drink
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CocktailViewModel(val peopleInSpaceRepository: PeopleInSpaceRepository) : ViewModel() {
    val cocktailApi = CocktailApiImpl()

    val peopleInSpace: StateFlow<List<Drink>> = flowgetDrinks2()
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    fun flowgetDrinks2(): Flow<List<Drink>> {
        return flow {
            emit( cocktailApi.getCocktails().drinks)
        }
    }

    fun flowgetDrinks(): Flow<CocktailResult> {
        return flow {
            emit( cocktailApi.getCocktails())
        }
    }

    fun getPersonImage(personName: String): String {
        return getPerson(personName )?.strDrinkThumb ?: ""
    }

    fun getPerson(personName: String): Drink? {
        return peopleInSpace.value.find { it.strDrink == personName}
    }
}
