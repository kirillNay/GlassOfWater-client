package nay.kirill.glassOfWater

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import nay.kirill.healthcare.domain.useCases.GetTodayParamsUseCase
import nay.kirill.healthcare.domain.useCases.UpdateTodayWaterUseCase
import nay.kirill.kmpArch.ViewModel

class GlassOfWaterViewModel(
    private val getTodayParamsUseCase: GetTodayParamsUseCase,
    private val updateTodayWaterUseCase: UpdateTodayWaterUseCase
): ViewModel() {

    private val _state = MutableStateFlow<GlassOfWaterState>(GlassOfWaterState.Loading)
    val state: StateFlow<GlassOfWaterState> = _state.apply { onEach { console.log(it.toString()) } }

    init {
        viewModelScope.launch {
            try {
                val params = getTodayParamsUseCase()
                _state.value = GlassOfWaterState.Content(params.waterCount)
            } catch (e: Throwable) {
                _state.value = GlassOfWaterState.Content(0)
            }
        }
    }

    fun increaseCount() {
        _state.value = state.value.copyContent { copy(count = count + 1) }
        updateCount()
    }

    fun decreaseCount() {
        _state.value = state.value.copyContent { copy(count = count - 1) }
        updateCount()
    }

    private fun updateCount() {
        (_state.value as? GlassOfWaterState.Content)?.count?.let {
            viewModelScope.launch { updateTodayWaterUseCase(it) }
        }
    }

}