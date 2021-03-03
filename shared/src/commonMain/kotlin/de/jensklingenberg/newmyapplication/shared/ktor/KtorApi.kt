package de.jensklingenberg.newmyapplication.shared.ktor

import de.jensklingenberg.newmyapplication.shared.response.BreedResult

interface KtorApi {
    suspend fun getJsonFromApi(): BreedResult
}
