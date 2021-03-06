package de.jensklingenberg.newmyapplication.shared.ktor

import de.jensklingenberg.newmyapplication.shared.models.CocktailResult
import de.jensklingenberg.newmyapplication.shared.models.MealResult
import io.ktor.client.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json

class MealApiImpl{

    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(Json {
                isLenient = true
                ignoreUnknownKeys = true
            }
            )
        }
    }

    suspend fun getCocktailByName(name:String): MealResult {
        return client.get("https://www.themealdb.com/api/json/v1/1/search.php?s=$name")
    }

    suspend fun getCocktailById(id:Int): MealResult {
        return client.get("https://www.themealdb.com/api/json/v1/1/search.php?s=$id")
    }

    suspend fun getCocktails(): MealResult {
        return client.get("https://www.themealdb.com/api/json/v1/1/search.php?s=")
    }

    //https://www.themealdb.com/images/ingredients/penne%20rigate.png
}
