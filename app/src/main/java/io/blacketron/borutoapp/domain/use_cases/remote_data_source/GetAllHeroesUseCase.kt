package io.blacketron.borutoapp.domain.use_cases.remote_data_source

import androidx.paging.PagingData
import io.blacketron.borutoapp.data.repository.MainRepository
import io.blacketron.borutoapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

class GetAllHeroesUseCase(private val repository: MainRepository) {

    operator fun invoke(): Flow<PagingData<Hero>> {
        return repository.getAllHeroes()
    }
}