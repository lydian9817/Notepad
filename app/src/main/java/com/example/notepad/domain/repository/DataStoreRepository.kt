package com.example.notepad.domain.repository

import androidx.datastore.preferences.core.Preferences
import com.example.notepad.data.data_store.UserPreferences
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {

    fun getPreferences(): Flow<UserPreferences?>

    suspend fun saveBooleanPreference(key: Preferences.Key<Boolean>, value: Boolean)

}