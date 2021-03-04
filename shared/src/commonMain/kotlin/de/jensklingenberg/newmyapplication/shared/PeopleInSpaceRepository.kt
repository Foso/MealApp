package de.jensklingenberg.newmyapplication.shared

import de.jensklingenberg.newmyapplication.shared.ktor.CocktailApiImpl
import de.jensklingenberg.newmyapplication.shared.response.CocktailResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class PeopleInSpaceRepository()  {
    private val peopleInSpaceApi: CocktailApiImpl = CocktailApiImpl()


    private val coroutineScope: CoroutineScope = MainScope()

    var peopleJob: Job? = null


    init {
        coroutineScope.launch {

        }
    }

    fun fetchPeopleAsFlow(): Flow<CocktailResult> {
        // the main reason we need to do this check is that sqldelight isn't currently
        // setup for javascript client
        return flow { emit(peopleInSpaceApi.getJsonFromApi()) }
    }


    // called from Kotlin/Native clients
    fun startObservingPeopleUpdates(success: (CocktailResult) -> Unit) {

        peopleJob = coroutineScope.launch {
            fetchPeopleAsFlow().collect {
                success(it)
            }
        }
    }

    fun stopObservingPeopleUpdates() {
        peopleJob?.cancel()
    }


    companion object {
        private const val POLL_INTERVAL = 10000L
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

