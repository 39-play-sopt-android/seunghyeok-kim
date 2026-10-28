package org.sopt.play.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
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
    }

    // 로그인 상태를 지속적으로 관찰할 수 있는 Flow. 저장된 값이 없으면 기본값 false 반환
    val isLoggedInFlow: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[IS_LOGGED_IN] ?: false
    }

    // 유저 목록 JSON 문자열을 관찰하여 List<UserEntity> 객체로 변환하여 방출하는 Flow
    val usersFlow: Flow<List<UserEntity>> = dataStore.data.map { preferences ->
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

    // 새로운 유저 정보를 DataStore에 저장하는 비동기 함수
    suspend fun saveUser(name: String, email: String, pw: String): UserEntity {
        var newUser = UserEntity(1, name, email, pw)
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
            // 기존 유저 목록에서 가장 큰 ID 값을 찾아 1을 더해 새로운 고유 ID 생성
            val newId = (currentUsers.maxOfOrNull { it.id } ?: 0) + 1
            newUser = UserEntity(newId, name, email, pw)
            val updatedUsers = currentUsers + newUser
            preferences[USERS_JSON] = Json.encodeToString(updatedUsers)
        }
        return newUser
    }

    // 사용자의 로그인 상태(true/false)를 DataStore에 갱신하는 비동기 함수
    suspend fun setLoggedIn(isLoggedIn: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN] = isLoggedIn
        }
    }
}
