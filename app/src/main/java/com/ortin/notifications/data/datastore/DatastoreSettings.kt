package com.ortin.notifications.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("user_prefs")

internal class UserPreferences(private val context: Context) {
    suspend fun saveUser(login: String, id: String) {
        context.dataStore.edit { prefs ->
            prefs[KEY_USER_LOGIN] = login
            prefs[KEY_USER_ID] = id
        }
    }

    val userLogin: Flow<String?> = context.dataStore.data.map { it[KEY_USER_LOGIN] }
    val userId: Flow<String?> = context.dataStore.data.map { it[KEY_USER_ID] }

    companion object {
        val KEY_USER_LOGIN = stringPreferencesKey("user_login")
        val KEY_USER_ID = stringPreferencesKey("user_id")
    }
}
