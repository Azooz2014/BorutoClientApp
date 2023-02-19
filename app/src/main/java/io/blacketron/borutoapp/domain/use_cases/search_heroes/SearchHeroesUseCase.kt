package io.blacketron.borutoapp.domain.use_cases.search_heroes

import androidx.paging.PagingData
import io.blacketron.borutoapp.data.repository.MainRepository
import io.blacketron.borutoapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

class SearchHeroesUseCase(private val repository: MainRepository) {

    operator fun invoke(query: String): Flow<PagingData<Hero>> {
        return repository.searchHeroes(query)
    }
}