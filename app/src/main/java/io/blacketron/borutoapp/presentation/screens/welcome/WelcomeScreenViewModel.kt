package io.blacketron.borutoapp.presentation.screens.welcome

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.blacketron.borutoapp.domain.use_cases.UseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeScreenViewModel @Inject constructor(private val useCases: UseCases): ViewModel(){

    private val TAG = "WelcomeScreenViewModel"

    private val _isFinished = MutableStateFlow(false)
    val isFinished: StateFlow<Boolean> = _isFinished.asStateFlow()

    fun welcomePageCompleted(isCompleted: Boolean){
        viewModelScope.launch(Dispatchers.IO) {
            useCases.welcomePageCompletedUseCase(isCompleted = isCompleted)

            _isFinished.update {
                true
            }

            Log.d(TAG, "WelcomeScreenViewModel Coroutine triggered!")
            Log.d(TAG,"Welcome page state saved to datastore: $isCompleted")
            Log.d(TAG, "WelcomeScreenViewModel CoroutineFinished value : ${isFinished.value}")
        }
    }
}