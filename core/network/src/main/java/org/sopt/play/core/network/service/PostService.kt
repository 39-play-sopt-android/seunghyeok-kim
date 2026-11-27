package org.sopt.play.core.network.service

import org.sopt.play.core.network.model.BaseResponse
import org.sopt.play.core.network.model.CreatePostRequestDto
import org.sopt.play.core.network.model.PostDetailResponseDto
import org.sopt.play.core.network.model.PostSliceResponseDto
import org.sopt.play.core.network.model.UpdatePostRequestDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface PostService {

    @GET("api/v1/posts")
    suspend fun getPosts(
        @Query("keyword") keyword: String? = null,
        @Query("cursor") cursor: Long? = null,
        @Query("size") size: Int = 20
    ): BaseResponse<PostSliceResponseDto>

    @POST("api/v1/posts")
    suspend fun createPost(
        @Body request: CreatePostRequestDto
    ): BaseResponse<PostDetailResponseDto>

    @PUT("api/v1/posts/{postId}")
    suspend fun updatePost(
        @Path("postId") postId: Long,
        @Body request: UpdatePostRequestDto
    ): BaseResponse<PostDetailResponseDto>

    @DELETE("api/v1/posts/{postId}")
    suspend fun deletePost(
        @Path("postId") postId: Long
    ): BaseResponse<Unit>
}
