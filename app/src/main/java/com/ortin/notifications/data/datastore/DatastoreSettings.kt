package com.ortin.notifications.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("user_prefs")

internal class UserPreferences(private val context: Context) {
    suspend fun saveUser(id: String, result: String) {
        context.dataStore.edit { prefs ->
            prefs[KEY_USER_ID] = id
            prefs[KEY_USER_RESULT] = result
        }
    }

    val userId: Flow<String?> = context.dataStore.data.map { it[KEY_USER_ID] }
    val userResult: Flow<String?> = context.dataStore.data.map { it[KEY_USER_RESULT] }

    companion object {
        val KEY_USER_ID = stringPreferencesKey("user_id")
        val KEY_USER_RESULT = stringPreferencesKey("user_result")
    }
}
