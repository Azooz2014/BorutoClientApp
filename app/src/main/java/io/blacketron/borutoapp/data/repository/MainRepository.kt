package io.blacketron.borutoapp.data.repository

import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun welcomePageCompleted(isCompleted: Boolean)
    fun isWelcomePageCompleted(): Flow<Boolean>
}