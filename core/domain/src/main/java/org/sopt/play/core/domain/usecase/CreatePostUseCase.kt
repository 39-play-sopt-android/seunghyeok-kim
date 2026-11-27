package org.sopt.play.core.domain.usecase

import org.sopt.play.core.domain.repository.PostRepository

class CreatePostUseCase(
    private val postRepository: PostRepository
) {
    suspend operator fun invoke(title: String, content: String): Result<Unit> {
        return postRepository.createPost(title, content)
    }
}
