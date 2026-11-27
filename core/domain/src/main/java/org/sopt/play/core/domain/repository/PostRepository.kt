package org.sopt.play.core.domain.repository

import org.sopt.play.core.domain.model.Post

interface PostRepository {
    suspend fun getPosts(keyword: String? = null, page: Int = 1, size: Int = 20): Result<List<Post>>
    suspend fun createPost(title: String, content: String): Result<Unit>
    suspend fun updatePost(postId: Long, title: String, content: String): Result<Unit>
    suspend fun deletePost(postId: Long): Result<Unit>
}
