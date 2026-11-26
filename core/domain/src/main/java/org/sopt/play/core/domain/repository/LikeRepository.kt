package org.sopt.play.core.domain.repository

import kotlinx.coroutines.flow.Flow
import org.sopt.play.core.datastore.LikeLocalDataSource

class LikeRepository(
    private val likeLocalDataSource: LikeLocalDataSource
) {
    val likedProductsFlow: Flow<Set<Int>> = likeLocalDataSource.likedProductsFlow

    suspend fun toggleLike(productId: Int) {
        likeLocalDataSource.toggleLike(productId)
    }
}
