package com.example.notepad.data.data_store.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.notepad.data.data_store.UserPreferences
import com.example.notepad.domain.repository.DataStoreRepository
import com.example.notepad.domain.util.PreferencesKeys
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : DataStoreRepository{

    override fun getPreferences() =
        dataStore.data.map { preferences ->
            UserPreferences(
                darkMode = preferences[PreferencesKeys.DARK_THEME] ?: false
            )
        }

    override suspend fun saveBooleanPreference(key: Preferences.Key<Boolean>, value: Boolean) {
        try {
            dataStore.edit { preferences ->
                preferences[key] = value
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}