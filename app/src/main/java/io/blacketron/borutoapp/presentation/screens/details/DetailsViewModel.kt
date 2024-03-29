package io.blacketron.borutoapp.presentation.screens.details

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.blacketron.borutoapp.domain.model.Hero
import io.blacketron.borutoapp.domain.use_cases.UseCases
import io.blacketron.borutoapp.util.DETAILS_SCREEN_ARGUMENT_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    val useCases: UseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _selectedHero: MutableStateFlow<Hero?> = MutableStateFlow(null)
    val selectedHero = _selectedHero

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent: SharedFlow<UiEvent> = _uiEvent.asSharedFlow()

    private val _colorPalette: MutableState<Map<String, String>> = mutableStateOf(mapOf())
    val colorPalette: State<Map<String, String>> = _colorPalette

    fun generateColorPalette() {
        viewModelScope.launch {
            _uiEvent.emit(UiEvent.GenerateColorPalette)
        }
    }

    fun setColorPalette(colors: Map<String, String>) {
        _colorPalette.value = colors
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val heroId = savedStateHandle.get<Int>(DETAILS_SCREEN_ARGUMENT_KEY)
            _selectedHero.value = heroId?.let { useCases.getSelectedHeroUseCase(heroId) }
            _selectedHero.value?.name?.let { Log.d("DetailsViewModel", "Hero name is: $it") }
        }
    }
}

sealed class UiEvent {
    object GenerateColorPalette : UiEvent()
}