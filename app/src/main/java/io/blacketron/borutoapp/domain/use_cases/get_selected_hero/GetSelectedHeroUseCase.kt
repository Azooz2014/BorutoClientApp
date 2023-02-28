package io.blacketron.borutoapp.domain.use_cases.get_selected_hero

import io.blacketron.borutoapp.data.repository.MainRepository

class GetSelectedHeroUseCase(private val repository: MainRepository) {
    suspend operator fun invoke(heroId: Int) = repository.getSelectedHero(heroId)
}