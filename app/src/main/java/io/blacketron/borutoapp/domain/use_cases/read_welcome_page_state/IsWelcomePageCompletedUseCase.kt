package io.blacketron.borutoapp.domain.use_cases.read_welcome_page_state

import io.blacketron.borutoapp.data.repository.MainRepository
import kotlinx.coroutines.flow.Flow

class IsWelcomePageCompletedUseCase(private val repository: MainRepository) {

    operator fun invoke(): Flow<Boolean> = repository.isWelcomePageCompleted()
}