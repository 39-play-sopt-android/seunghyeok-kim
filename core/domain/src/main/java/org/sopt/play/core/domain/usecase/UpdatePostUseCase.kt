package org.sopt.play.core.domain.usecase

import org.sopt.play.core.domain.repository.PostRepository

class UpdatePostUseCase(
    private val postRepository: PostRepository
) {
    suspend operator fun invoke(postId: Long, title: String, content: String): Result<Unit> {
        return postRepository.updatePost(postId, title, content)
    }
}
