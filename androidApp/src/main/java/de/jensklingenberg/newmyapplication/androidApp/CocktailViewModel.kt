package de.jensklingenberg.newmyapplication.androidApp

import androidx.lifecycle.ViewModel
import de.jensklingenberg.newmyapplication.shared.ktor.CocktailApiImpl
import de.jensklingenberg.newmyapplication.shared.response.CocktailResult
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class CocktailViewModel : ViewModel() {

    val cocktailApi = CocktailApiImpl()

    fun getDrinks() {
        GlobalScope.launch {
            val teest = cocktailApi.getJsonFromApi()
            teest
        }
    }

    fun flowgetDrinks(): Flow<CocktailResult> {
        return flow {
            emit( cocktailApi.getJsonFromApi())
        }
    }
}
