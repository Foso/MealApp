package de.jensklingenberg.newmyapplication.shared.response

import kotlinx.serialization.Serializable

@Serializable
data class CocktailResult(
    val drinks: List<Drink>,
)

@Serializable
data class Drink(
    var strDrink: String,
    var idDrink : Int
)
