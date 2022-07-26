package io.blacketron.borutoapp.domain.use_cases

import io.blacketron.borutoapp.domain.use_cases.read_welcome_page_state.IsWelcomePageCompletedUseCase
import io.blacketron.borutoapp.domain.use_cases.save_welcome_page_state.WelcomePageCompletedUseCase

data class UseCase(
    val welcomePageCompletedUseCase: WelcomePageCompletedUseCase,
    val isWelcomePageCompletedUseCase: IsWelcomePageCompletedUseCase
)
