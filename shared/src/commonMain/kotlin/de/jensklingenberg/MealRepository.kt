package de.jensklingenberg

import de.jensklingenberg.mealdbapi.*
import de.jensklingenberg.network.model.Meal
import de.jensklingenberg.network.model.mapToMeal


class MealRepository : MealDataSource {

    private val mealApi: MealApiImpl = MealApiImpl()

    override suspend fun getMeals(): List<Meal> = mealApi.getMeals().meals?.map { it.mapToMeal() } ?: emptyList()
    override fun getIngredientImageUrl(name: String): String =
        "https://www.themealdb.com/images/ingredients/$name.png"

    override suspend fun getCategories(): List<Category> = mealApi.getCategories().categories
    override suspend fun getMealsByCategory(categoryName: String): List<Meal> =
        mealApi.getMealsByCategory(categoryName).meals?.map { it.mapToMeal() } ?: emptyList()

    override suspend fun getMealsByName(categoryName: String): List<Meal> {
        return mealApi.getMealsByName(categoryName).meals?.map { it.mapToMeal() } ?: emptyList()
    }

}

