package io.blacketron.borutoapp.data.repository

import androidx.paging.PagingData
import io.blacketron.borutoapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun welcomePageCompleted(isCompleted: Boolean)
    fun isWelcomePageCompleted(): Flow<Boolean>
    fun getAllHeroes(): Flow<PagingData<Hero>>
    fun searchHeroes(query: String): Flow<PagingData<Hero>>
}