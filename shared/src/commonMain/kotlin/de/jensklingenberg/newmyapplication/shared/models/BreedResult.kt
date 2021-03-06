package de.jensklingenberg.newmyapplication.shared.models

import kotlinx.serialization.Serializable

@Serializable
data class CocktailResult(
    val drinks: List<Drink>,
)

@Serializable
data class Drink(
    var strDrink: String,
    var idDrink: Int,
    var strDrinkThumb: String,


)

fun Meal.getIngredients():List<String>{
    val ingredientsList = mutableListOf<String>()

    strIngredient1?.let {
        ingredientsList.add(it)
    }

    strIngredient2?.let {
        ingredientsList.add(it)
    }

    strIngredient3?.let {
        ingredientsList.add(it)
    }

    strIngredient4?.let {
        ingredientsList.add(it)
    }

    strIngredient5?.let {
        ingredientsList.add(it)
    }
    strIngredient6?.let {
        ingredientsList.add(it)
    }
    strIngredient7?.let {
        ingredientsList.add(it)
    }

    strIngredient8?.let {
        ingredientsList.add(it)
    }

    strIngredient9?.let {
        ingredientsList.add(it)
    }

    return ingredientsList
}

@Serializable
data class Meal(
    var strMeal: String,
    var idMeal: Int,
    var strMealThumb: String,
    var strIngredient1: String?,
    var strIngredient2: String?,
    var strIngredient3: String?,
    var strIngredient4: String?,
    var strIngredient5: String?,
    var strIngredient6: String?,
    var strIngredient7: String?,
    var strIngredient8: String?,
    var strIngredient9: String?,
    var strTags: String?,
    var strCategory: String,
    var strInstructions: String?
)

@Serializable
data class MealResult(
    val meals: List<Meal>,
)