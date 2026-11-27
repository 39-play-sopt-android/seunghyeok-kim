package org.sopt.play.core.data.repository

import kotlinx.coroutines.flow.first
import org.sopt.play.core.data.mapper.toModel
import org.sopt.play.core.datastore.UserLocalDataSource
import org.sopt.play.core.domain.model.Post
import org.sopt.play.core.domain.repository.PostRepository
import org.sopt.play.core.network.model.CreatePostRequestDto
import org.sopt.play.core.network.model.UpdatePostRequestDto
import org.sopt.play.core.network.service.PostService

class PostRepositoryImpl(
    private val postService: PostService,
    private val userLocalDataSource: UserLocalDataSource
) : PostRepository {
    override suspend fun getPosts(keyword: String?, page: Int, size: Int): Result<List<Post>> {
        return runCatching {
            val response = postService.getPosts(keyword = keyword, size = size)
            response.data?.posts?.map { it.toModel() } ?: emptyList()
        }
    }

    override suspend fun createPost(title: String, content: String): Result<Unit> {
        return runCatching {
            val authorName = userLocalDataSource.currentUserNameFlow.first()
            postService.createPost(CreatePostRequestDto(title = title, content = content, author = authorName))
            Unit
        }
    }

    override suspend fun updatePost(postId: Long, title: String, content: String): Result<Unit> {
        return runCatching {
            postService.updatePost(postId, UpdatePostRequestDto(title = title, content = content))
            Unit
        }
    }

    override suspend fun deletePost(postId: Long): Result<Unit> {
        return runCatching {
            postService.deletePost(postId)
            Unit
        }
    }
}
