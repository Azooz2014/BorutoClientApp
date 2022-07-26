package io.blacketron.borutoapp.data.local.preferences

import kotlinx.coroutines.flow.Flow

interface DataStoreOperations {

    suspend fun welcomePageCompleted(isCompleted: Boolean)
    fun isWelcomePageCompleted(): Flow<Boolean>
}