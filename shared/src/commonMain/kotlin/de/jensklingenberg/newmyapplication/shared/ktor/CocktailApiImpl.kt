package de.jensklingenberg.newmyapplication.shared.ktor

import co.touchlab.stately.ensureNeverFrozen
import de.jensklingenberg.newmyapplication.shared.response.CocktailResult
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import kotlinx.serialization.json.Json

class CocktailApiImpl{

    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(Json {
                isLenient = true
                ignoreUnknownKeys = true
            }
            )
        }
    }

    init {
        ensureNeverFrozen()
    }

    suspend fun getCocktailByName(name:String): CocktailResult {
        return client.get("https://www.thecocktaildb.com/api/json/v1/1/search.php?s=$name")
    }

    suspend fun getCocktailById(id:Int): CocktailResult {
        return client.get("https://www.thecocktaildb.com/api/json/v1/1/search.php?s=$id")
    }

    suspend fun getCocktails(): CocktailResult {
        return client.get("https://www.thecocktaildb.com/api/json/v1/1/search.php?s=")
    }
}
