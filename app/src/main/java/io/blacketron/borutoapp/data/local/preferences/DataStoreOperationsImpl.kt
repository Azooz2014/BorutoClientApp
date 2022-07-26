package io.blacketron.borutoapp.data.local.preferences

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import io.blacketron.borutoapp.util.DATASTORE_PREFERENCES_NAME
import io.blacketron.borutoapp.util.DATASTORE_PREFERENCES_WELCOME_PAGE_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_PREFERENCES_NAME)

class DataStoreOperationsImpl(context: Context) : DataStoreOperations {

    private object PreferencesKeys {
        val WELCOME_PAGE_COMPLETION_KEY = booleanPreferencesKey(
            name = DATASTORE_PREFERENCES_WELCOME_PAGE_KEY
        )
    }

    private val dataStore = context.dataStore

    override suspend fun welcomePageCompleted(isCompleted: Boolean) {

        dataStore.edit { preferences ->
            preferences[PreferencesKeys.WELCOME_PAGE_COMPLETION_KEY] = isCompleted
            Log.i(
                this.toString(),
                "datastore preferences saved: ${preferences[PreferencesKeys.WELCOME_PAGE_COMPLETION_KEY].toString()}"
            )
        }
    }

    override fun isWelcomePageCompleted(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->

                val isCompleted = preferences[PreferencesKeys.WELCOME_PAGE_COMPLETION_KEY] ?: false
//                Log.i(this.toString(), "datastore preferences retrieved: $isCompleted")
                isCompleted
            }
    }
}