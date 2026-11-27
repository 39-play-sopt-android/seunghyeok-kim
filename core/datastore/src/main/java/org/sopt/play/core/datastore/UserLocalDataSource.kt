package org.sopt.play.core.datastore

import kotlinx.coroutines.flow.Flow
import org.sopt.play.core.datastore.model.UserEntity

class UserLocalDataSource(
    private val userPreferences: UserPreferences
) {
    val isLoggedInFlow: Flow<Boolean> = userPreferences.isLoggedInFlow
    val currentUserNameFlow: Flow<String> = userPreferences.currentUserNameFlow
    val authCodeFlow: Flow<String> = userPreferences.authCodeFlow
    val usersFlow: Flow<List<UserEntity>> = userPreferences.usersFlow

    suspend fun saveUser(name: String, email: String, pw: String): UserEntity {
        return userPreferences.saveUser(name, email, pw)
    }

    suspend fun saveCurrentUserName(name: String) {
        userPreferences.saveCurrentUserName(name)
    }

    suspend fun saveAuthCode(code: String) {
        userPreferences.saveAuthCode(code)
    }

    suspend fun setLoggedIn(isLoggedIn: Boolean) {
        userPreferences.setLoggedIn(isLoggedIn)
    }
}
