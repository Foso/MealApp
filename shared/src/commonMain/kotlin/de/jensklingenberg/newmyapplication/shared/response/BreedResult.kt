package de.jensklingenberg.newmyapplication.shared.response

import kotlinx.serialization.Serializable

@Serializable
data class BreedResult(
    val message: HashMap<String, List<String>>,
    var status: String
)


@Serializable
data class CocktailResult(
    val drinks: List<Drink>,
)

@Serializable
data class Drink(
    var strDrink: String,
    var idDrink : Int
)

@Serializable
data class Post(val userId: Int, val id: Int, val title: String, val body: String)