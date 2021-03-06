package de.jensklingenberg.newmyapplication.shared

import de.jensklingenberg.newmyapplication.shared.ktor.CocktailApiImpl
import de.jensklingenberg.newmyapplication.shared.models.CocktailResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class PeopleInSpaceRepository()  {
    private val peopleInSpaceApi: CocktailApiImpl = CocktailApiImpl()


    private val coroutineScope: CoroutineScope = MainScope()

    var peopleJob: Job? = null


    // called from Kotlin/Native clients
    fun startObservingPeopleUpdates(success: (CocktailResult) -> Unit) {

        peopleJob = coroutineScope.launch {
            flow { emit(peopleInSpaceApi.getCocktails()) }.collect {
                success(it)
            }
        }
    }

    fun stopObservingPeopleUpdates() {
        peopleJob?.cancel()
    }
}


class KotlinNativeFlowWrapper<T>(private val flow: Flow<T>) {
    fun subscribe(
        scope: CoroutineScope,
        onEach: (item: T) -> Unit,
        onComplete: () -> Unit,
        onThrow: (error: Throwable) -> Unit
    ) = flow
        .onEach { onEach(it) }
        .catch { onThrow(it) }
        .onCompletion { onComplete() }
        .launchIn(scope)
}

