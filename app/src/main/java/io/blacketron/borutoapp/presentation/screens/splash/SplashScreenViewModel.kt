package io.blacketron.borutoapp.presentation.screens.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.blacketron.borutoapp.domain.use_cases.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(private val useCase: UseCase) : ViewModel() {

    private val TAG = "SplashScreenViewModel"

    private val _isWelcomePageCompleted = MutableStateFlow(false)
    val isWelcomePageCompleted: StateFlow<Boolean> = _isWelcomePageCompleted

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _isWelcomePageCompleted.value =
                useCase.isWelcomePageCompletedUseCase().stateIn(scope = viewModelScope).value
            Log.d(TAG, "SplashScreenViewModel Coroutine triggered!")
            Log.d(TAG, "Welcome page state retrieved from datastore is: ${isWelcomePageCompleted.value}")
        }
    }
}