package de.jensklingenberg

import de.jensklingenberg.mealdbapi.Category
import de.jensklingenberg.network.model.Meal

interface MealDataSource {
    suspend fun getMeals(): List<Meal>
    fun getIngredientImageUrl(name: String): String
    suspend fun getCategories(): List<Category>
    suspend fun getMealsByCategory(categoryName: String): List<Meal>
    suspend fun getMealsByName(categoryName: String): List<Meal>
}