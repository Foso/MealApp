package de.jensklingenberg.newmyapplication.shared.ktor

import de.jensklingenberg.newmyapplication.shared.models.CategoryResult
import de.jensklingenberg.newmyapplication.shared.models.MealResult
import io.ktor.client.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json

class MealApiImpl{

    private val baseUrl = "https://www.themealdb.com/api/json/v1/1"

    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(Json {
                isLenient = true
                ignoreUnknownKeys = true
            }
            )
        }
    }

    suspend fun getMeals(): MealResult {
        return client.get("$baseUrl/search.php?s=")
    }

    suspend fun getMealsByCategory(categoryName:String): MealResult {
        return client.get("$baseUrl/filter.php?c=$categoryName")
    }

    suspend fun getCategories(): CategoryResult {
        return client.get("$baseUrl/categories.php")
    }

    suspend fun getMealsByName(categoryName:String): MealResult {
        return client.get("$baseUrl/search.php?s=$categoryName")
    }

    //https://www.themealdb.com/images/ingredients/penne%20rigate.png
}
