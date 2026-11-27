package org.sopt.play.core.domain.usecase

import org.sopt.play.core.domain.model.Post
import org.sopt.play.core.domain.repository.PostRepository

class GetPostsUseCase(
    private val postRepository: PostRepository
) {
    suspend operator fun invoke(keyword: String? = null, page: Int = 1, size: Int = 20): Result<List<Post>> {
        return postRepository.getPosts(keyword, page, size)
    }
}
