package org.sopt.play.core.domain.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import org.sopt.play.core.datastore.UserLocalDataSource

sealed interface LoginResult {
    data object Success : LoginResult
    data object UserNotFound : LoginResult
    data object InvalidPassword : LoginResult
}

class AuthRepository(
    private val userLocalDataSource: UserLocalDataSource
) {
    val isLoggedInFlow: Flow<Boolean> = userLocalDataSource.isLoggedInFlow

    suspend fun register(name: String, email: String, pw: String) {
        userLocalDataSource.saveUser(name, email, pw)
    }

    suspend fun login(email: String, pw: String): LoginResult {
        val foundUser = userLocalDataSource.usersFlow.first().find { it.email == email }
        return when {
            foundUser == null -> LoginResult.UserNotFound
            foundUser.password != pw -> LoginResult.InvalidPassword
            else -> {
                userLocalDataSource.setLoggedIn(true)
                LoginResult.Success
            }
        }
    }
}
