package io.blacketron.borutoapp.data.repository

import io.blacketron.borutoapp.data.local.preferences.DataStoreOperations
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val dataStoreOperations: DataStoreOperations): MainRepository {
    override suspend fun welcomePageCompleted(isCompleted: Boolean) {
        dataStoreOperations.welcomePageCompleted(isCompleted = isCompleted)
    }

    override fun isWelcomePageCompleted(): Flow<Boolean> {
        return dataStoreOperations.isWelcomePageCompleted()
    }
}