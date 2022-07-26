package io.blacketron.borutoapp.presentation.screens.welcome

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.blacketron.borutoapp.domain.use_cases.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeScreenViewModel @Inject constructor(private val useCase: UseCase): ViewModel(){

    private val TAG = "WelcomeScreenViewModel"

    private val _isFinished = MutableStateFlow(false)
    val isFinished: StateFlow<Boolean> = _isFinished.asStateFlow()

    fun welcomePageCompleted(isCompleted: Boolean){
        viewModelScope.launch(Dispatchers.IO) {
            useCase.welcomePageCompletedUseCase(isCompleted = isCompleted)

            _isFinished.update {
                true
            }

            Log.d(TAG, "WelcomeScreenViewModel Coroutine triggered!")
            Log.d(TAG,"Welcome page state saved to datastore: $isCompleted")
            Log.d(TAG, "WelcomeScreenViewModel CoroutineFinished value : ${isFinished.value}")
        }
    }
}