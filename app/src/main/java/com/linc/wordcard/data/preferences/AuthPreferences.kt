package com.linc.wordcard.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject


class AuthPreferences @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    companion object {
        private val USER_ID_KEY = stringPreferencesKey("user_id")
    }

    suspend fun putUserId(id: String) {
        dataStore.edit { preferences ->
            preferences[USER_ID_KEY] = id
        }
    }

    suspend fun getUserId(): String {
        val preferences = dataStore.data.first()
        return preferences[USER_ID_KEY].orEmpty()
    }

    suspend fun clear() {
        dataStore.edit { it.clear() }
    }

}