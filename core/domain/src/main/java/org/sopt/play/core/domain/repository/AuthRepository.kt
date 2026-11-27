package org.sopt.play.core.domain.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import org.sopt.play.core.datastore.UserLocalDataSource
import org.sopt.play.core.network.model.IssueAuthCodeRequestDto
import org.sopt.play.core.network.service.MemberService

sealed interface LoginResult {
    data object Success : LoginResult
    data object UserNotFound : LoginResult
    data object InvalidPassword : LoginResult
}

class AuthRepository(
    private val userLocalDataSource: UserLocalDataSource,
    private val memberService: MemberService
) {
    val isLoggedInFlow: Flow<Boolean> = userLocalDataSource.isLoggedInFlow

    suspend fun register(name: String, email: String, pw: String) {
        userLocalDataSource.saveUser(name, email, pw)
    }

    suspend fun issueAuthCode(part: String): Result<Unit> = runCatching {
        memberService.getAuthorizationCode(IssueAuthCodeRequestDto(part = part)).data?.authorizationCode?.let { code ->
            userLocalDataSource.saveAuthCode(code)
        }
    }

    suspend fun login(email: String, pw: String): LoginResult {
        val users = userLocalDataSource.usersFlow.first()
        val foundUser = users.find { it.email == email }

        return when {
            foundUser == null -> LoginResult.UserNotFound
            foundUser.password != pw -> LoginResult.InvalidPassword
            else -> {
                issueAuthCode("ANDROID")
                userLocalDataSource.saveCurrentUserName(foundUser.name)
                userLocalDataSource.setLoggedIn(true)
                LoginResult.Success
            }
        }
    }
}
