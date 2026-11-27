package org.sopt.play.core.domain.usecase

import org.sopt.play.core.domain.repository.PostRepository

class DeletePostUseCase(
    private val postRepository: PostRepository
) {
    suspend operator fun invoke(postId: Long): Result<Unit> {
        return postRepository.deletePost(postId)
    }
}
