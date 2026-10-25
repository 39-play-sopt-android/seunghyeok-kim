package org.sopt.play.core.datastore

import kotlinx.coroutines.flow.Flow
import org.sopt.play.core.datastore.model.UserEntity

class UserLocalDataSource(
    private val userPreferences: UserPreferences
) {
    val isLoggedInFlow: Flow<Boolean> = userPreferences.isLoggedInFlow
    val usersFlow: Flow<List<UserEntity>> = userPreferences.usersFlow

    suspend fun saveUser(name: String, email: String, pw: String): UserEntity {
        return userPreferences.saveUser(name, email, pw)
    }

    suspend fun setLoggedIn(isLoggedIn: Boolean) {
        userPreferences.setLoggedIn(isLoggedIn)
    }
}
