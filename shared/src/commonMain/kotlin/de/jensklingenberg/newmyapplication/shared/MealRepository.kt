package de.jensklingenberg.newmyapplication.shared

import de.jensklingenberg.newmyapplication.shared.ktor.MealApiImpl
import de.jensklingenberg.newmyapplication.shared.models.Category
import de.jensklingenberg.newmyapplication.shared.models.Meal
import de.jensklingenberg.newmyapplication.shared.models.MealResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

interface MealDataSource {
    suspend fun getMeals(): List<Meal>
    fun getIngredientImageUrl(name: String): String
    suspend fun getCategories(): List<Category>
    suspend fun getMealsByCategory(categoryName: String): List<Meal>
    suspend fun getMealsByName(categoryName: String): List<Meal>

}

class MealRepository : MealDataSource {

    private val mealApi: MealApiImpl = MealApiImpl()

    override suspend fun getMeals(): List<Meal> = mealApi.getMeals().meals ?: emptyList()
    override fun getIngredientImageUrl(name: String): String =
        "https://www.themealdb.com/images/ingredients/$name.png"

    override suspend fun getCategories(): List<Category> = mealApi.getCategories().categories
    override suspend fun getMealsByCategory(categoryName: String): List<Meal> =
        mealApi.getMealsByCategory(categoryName).meals ?: emptyList()

    override suspend fun getMealsByName(categoryName: String): List<Meal> {
        return mealApi.getMealsByName(categoryName).meals ?: emptyList()
    }

}

