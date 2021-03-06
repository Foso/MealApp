package de.jensklingenberg.newmyapplication.shared

import de.jensklingenberg.newmyapplication.shared.ktor.MealApiImpl
import de.jensklingenberg.newmyapplication.shared.models.MealResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

interface MealDataSource {
    suspend fun getMeals(): MealResult
    fun getIngredientImageUrl(name: String): String
}

class MealRepository : MealDataSource {

    private val mealApi: MealApiImpl = MealApiImpl()

    override suspend fun getMeals(): MealResult = mealApi.getMeals()
    override fun getIngredientImageUrl(name: String): String {
        return "https://www.themealdb.com/images/ingredients/$name.png"
    }

}


class KotlinNativeFlowWrapper<T>(private val flow: Flow<T>) {
    fun subscribe(
        scope: CoroutineScope,
        onEach: (item: T) -> Unit,
        onComplete: () -> Unit,
        onThrow: (error: Throwable) -> Unit
    ) = flow
        .onEach { onEach(it) }
        .catch { onThrow(it) }
        .onCompletion { onComplete() }
        .launchIn(scope)
}

