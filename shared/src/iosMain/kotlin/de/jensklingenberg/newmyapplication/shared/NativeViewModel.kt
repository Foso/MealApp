package de.jensklingenberg.newmyapplication.shared


import co.touchlab.stately.ensureNeverFrozen
import de.jensklingenberg.newmyapplication.shared.ktor.CocktailApiImpl
import de.jensklingenberg.newmyapplication.shared.response.CocktailResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class NativeViewModel(
    private val onLoading: () -> Unit,
    private val onSuccess: (CocktailResult) -> Unit,
    private val onError: (String) -> Unit,
    private val onEmpty: () -> Unit
)  {


    private val scope = MainScope(Dispatchers.Main)
    private val breedModel: CocktailApiImpl = CocktailApiImpl()
    private val _breedStateFlow: MutableStateFlow<DataState<CocktailResult>> = MutableStateFlow(
        DataState.Loading
    )

    init {
        ensureNeverFrozen()
        observeBreeds()
    }

    fun observeBreeds() {
        scope.launch {

            flowOf(
                breedModel.getJsonFromApi()
            ).collect { dataState ->
                _breedStateFlow.value = DataState.Success(dataState)
            }
        }
        scope.launch {

            _breedStateFlow.collect { dataState ->
                when (dataState) {
                    is DataState.Success -> {

                        onSuccess(dataState.data)
                    }
                    is DataState.Error -> {

                        onError(dataState.exception)
                    }
                    DataState.Empty -> {

                        onEmpty()
                    }
                    DataState.Loading -> {
                        onLoading()
                    }
                }
            }
        }
    }



    fun onDestroy() {
        scope.onDestroy()
    }
}
