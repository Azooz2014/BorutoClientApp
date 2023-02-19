package io.blacketron.borutoapp.domain.use_cases

import io.blacketron.borutoapp.domain.use_cases.read_welcome_page_state.IsWelcomePageCompletedUseCase
import io.blacketron.borutoapp.domain.use_cases.remote_data_source.GetAllHeroesUseCase
import io.blacketron.borutoapp.domain.use_cases.save_welcome_page_state.WelcomePageCompletedUseCase
import io.blacketron.borutoapp.domain.use_cases.search_heroes.SearchHeroesUseCase

data class UseCases(
    val welcomePageCompletedUseCase: WelcomePageCompletedUseCase,
    val isWelcomePageCompletedUseCase: IsWelcomePageCompletedUseCase,
    val getAllHeroesUseCase: GetAllHeroesUseCase,
    val searchHeroesUseCase: SearchHeroesUseCase
)
