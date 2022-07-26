package io.blacketron.borutoapp.domain.use_cases.save_welcome_page_state

import io.blacketron.borutoapp.data.repository.MainRepository

class WelcomePageCompletedUseCase(private val repository: MainRepository) {
    suspend operator fun invoke(isCompleted: Boolean) {
        repository.welcomePageCompleted(isCompleted = isCompleted)
    }
}