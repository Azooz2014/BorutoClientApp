package io.blacketron.borutoapp.data.repository

import androidx.paging.PagingData
import io.blacketron.borutoapp.data.local.preferences.DataStoreOperations
import io.blacketron.borutoapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val dataStoreOperations: DataStoreOperations,
    private val remoteDataSource: RemoteDataSource
    ): MainRepository {
    override suspend fun welcomePageCompleted(isCompleted: Boolean) {
        dataStoreOperations.welcomePageCompleted(isCompleted = isCompleted)
    }

    override fun isWelcomePageCompleted(): Flow<Boolean> {
        return dataStoreOperations.isWelcomePageCompleted()
    }

    override fun getAllHeroes(): Flow<PagingData<Hero>>{
        return remoteDataSource.getAllHeroes()
    }
}