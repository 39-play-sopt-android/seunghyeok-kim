package org.sopt.play.core.domain.usecase

import org.sopt.play.core.domain.repository.AuthRepository

class IssueAuthCodeUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(part: String = "ANDROID"): Result<Unit> {
        return authRepository.issueAuthCode(part)
    }
}
