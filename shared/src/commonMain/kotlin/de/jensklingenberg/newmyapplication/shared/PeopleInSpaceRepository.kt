package de.jensklingenberg.newmyapplication.shared

import de.jensklingenberg.newmyapplication.shared.ktor.CocktailApiImpl
import de.jensklingenberg.newmyapplication.shared.models.CocktailResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

val personImages = mapOf(
    "Chris Cassidy" to "https://www.nasa.gov/sites/default/files/styles/side_image/public/thumbnails/image/9368855148_f79942efb7_o.jpg?itok=-w5yoryN",
    "Anatoly Ivanishin" to "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/Anatoli_Ivanishin_2011.jpg/440px-Anatoli_Ivanishin_2011.jpg",
    "Ivan Vagner" to "http://www.spacefacts.de/more/cosmonauts/photo/vagner_ivan_3.jpg",
    "Sergey Ryzhikov" to "https://spaceflight101.com/iss-expedition-50/wp-content/uploads/sites/118/2016/11/jsc2016e105228.jpg",
    "Kate Rubins" to "https://spaceflight101.com/iss-expedition-49/wp-content/uploads/sites/110/2016/09/26720141242_be992e9a20_o-768x1152.jpg",
    "Sergey Kud-Sverchkov" to "https://www.esa.int/var/esa/storage/images/esa_multimedia/images/2014/08/sergey_kud-sverchkov/14716838-1-eng-GB/Sergey_Kud-Sverchkov_pillars.jpg",
    "Mike Hopkins" to "https://pbs.twimg.com/media/Em5EbQOVEAAdZ0h?format=jpg&name=medium",
    "Victor Glover" to "https://pbs.twimg.com/media/Em5EbSnUYAEAgyl?format=jpg&name=medium",
    "Shannon Walker" to "https://pbs.twimg.com/media/Em5EbQPVoAATIx8?format=jpg&name=medium",
    "Soichi Noguchi" to "https://pbs.twimg.com/media/Em5EbSoVcAA3R2F?format=jpg&name=medium"
)

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

    fun getPersonImage(personName: String): String {
        return personImages[personName] ?: ""
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

