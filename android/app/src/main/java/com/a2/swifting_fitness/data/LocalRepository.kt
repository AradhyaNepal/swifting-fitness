package com.a2.swifting_fitness.data

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class LocalRepository(private val context: Context) {
    val ISFIRSTTIME = booleanPreferencesKey("isFirstTime")
    val isFirstTime: Flow<Boolean> = context.dataStore.data.map {
            preferences -> preferences[ISFIRSTTIME] ?: true
    }

    suspend fun setNotFirstTime() {
        context.dataStore.edit { settings ->
            settings[ISFIRSTTIME] = false
        }
    }
}