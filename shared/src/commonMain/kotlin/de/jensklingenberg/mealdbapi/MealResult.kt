package de.jensklingenberg.mealdbapi

import kotlinx.serialization.Serializable

data class Ingredient(val name: String, val measure: String = "")

fun ApiMeal.tags(): List<String> {
    return strTags?.split(",") ?: emptyList()
}

fun ApiMeal.getIngredients(): List<Ingredient> {
    val ingredientsList = mutableListOf<Ingredient>()
    strIngredient1?.let {
        ingredientsList.add(Ingredient(it, strMeasure1 ?: ""))
    }

    strIngredient2?.let {
        ingredientsList.add(Ingredient(it, strMeasure2 ?: ""))
    }
    strIngredient3?.let {
        ingredientsList.add(Ingredient(it, strMeasure3 ?: ""))
    }
    strIngredient4?.let {
        ingredientsList.add(Ingredient(it, strMeasure3 ?: ""))
    }


    return ingredientsList
}

/**
 * Dto from meal api
 */
@Serializable
data class ApiMeal(
    var strMeal: String,
    var idMeal: Int,
    var strMealThumb: String,
    var strIngredient1: String? = "",
    var strIngredient2: String? = "",
    var strIngredient3: String? = "",
    var strIngredient4: String? = "",
    var strIngredient5: String? = "",
    var strIngredient6: String? = "",
    var strIngredient7: String? = "",
    var strIngredient8: String? = "",
    var strIngredient9: String? = "",
    var strIngredient10: String? = "",
    var strIngredient11: String? = "",
    var strIngredient12: String? = "",
    var strIngredient13: String? = "",
    var strIngredient14: String? = "",
    var strIngredient15: String? = "",
    var strIngredient16: String? = "",
    var strIngredient17: String? = "",
    var strIngredient18: String? = "",
    var strIngredient19: String? = "",
    var strIngredient20: String? = "",
    var strMeasure1: String? = "",
    var strMeasure2: String? = "",
    var strMeasure3: String? = "",
    var strMeasure4: String? = "",
    var strMeasure5: String? = "",
    var strMeasure6: String? = "",
    var strMeasure7: String? = "",
    var strMeasure8: String? = "",
    var strMeasure9: String? = "",
    var strMeasure10: String? = "",
    var strMeasure11: String? = "",
    var strMeasure12: String? = "",
    var strMeasure13: String? = "",
    var strMeasure14: String? = "",
    var strMeasure15: String? = "",
    var strMeasure16: String? = "",
    var strMeasure17: String? = "",
    var strMeasure18: String? = "",
    var strMeasure19: String? = "",
    var strMeasure20: String? = "",
    var strTags: String? = "",
    var strCategory: String = "",
    var strInstructions: String = ""
)


@Serializable
data class MealResult(
    val meals: List<ApiMeal>?,
)

@Serializable
data class CategoryResult(
    val categories: List<Category>,
)

@Serializable
data class Category(
    var idCategory: String,
    var strCategory: String,
    var strCategoryThumb: String,
    var strCategoryDescription: String,
)