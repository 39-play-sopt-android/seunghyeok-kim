package org.sopt.play.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.sopt.play.core.datastore.model.UserEntity

class UserPreferences(
    private val dataStore: DataStore<Preferences>
) {
    companion object {
        private val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
        private val USERS_JSON = stringPreferencesKey("users_json")
        private val CURRENT_USER_NAME = stringPreferencesKey("current_user_name")
        private val AUTH_CODE = stringPreferencesKey("auth_code")
    }

    val isLoggedInFlow: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[IS_LOGGED_IN] ?: false
    }

    val currentUserNameFlow: Flow<String> = dataStore.data.map { preferences ->
        preferences[CURRENT_USER_NAME] ?: ""
    }

    val authCodeFlow: Flow<String> = dataStore.data.map { preferences ->
        preferences[AUTH_CODE] ?: ""
    }

    val usersFlow: Flow<List<UserEntity>> = dataStore.data
        .catch { exception ->
            if (exception is Exception) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val jsonString = preferences[USERS_JSON]
            if (jsonString.isNullOrBlank()) {
                emptyList()
            } else {
                try {
                    Json.decodeFromString<List<UserEntity>>(jsonString)
                } catch (e: Exception) {
                    emptyList()
                }
            }
        }

    suspend fun saveUser(name: String, email: String, pw: String): UserEntity {
        var newUser: UserEntity? = null
        dataStore.edit { preferences ->
            val jsonString = preferences[USERS_JSON]
            val currentUsers = if (jsonString.isNullOrBlank()) {
                emptyList()
            } else {
                try {
                    Json.decodeFromString<List<UserEntity>>(jsonString)
                } catch (e: Exception) {
                    emptyList()
                }
            }
            val newId = (currentUsers.maxOfOrNull { it.id } ?: 0) + 1
            val createdUser = UserEntity(newId, name, email, pw)
            val updatedUsers = currentUsers + createdUser
            preferences[USERS_JSON] = Json.encodeToString(updatedUsers)
            newUser = createdUser
        }
        return requireNotNull(newUser)
    }

    suspend fun saveCurrentUserName(name: String) {
        dataStore.edit { preferences ->
            preferences[CURRENT_USER_NAME] = name
        }
    }

    suspend fun saveAuthCode(code: String) {
        dataStore.edit { preferences ->
            preferences[AUTH_CODE] = code
        }
    }

    suspend fun setLoggedIn(isLoggedIn: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN] = isLoggedIn
        }
    }
}
