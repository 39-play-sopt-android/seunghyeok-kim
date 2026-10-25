package org.sopt.play.core.datastore

import kotlinx.coroutines.flow.Flow

class LikeLocalDataSource(
    private val likePreferences: LikePreferences
) {
    val likedProductsFlow: Flow<Set<Int>> = likePreferences.likedProductsFlow

    suspend fun toggleLike(productId: Int) {
        likePreferences.toggleLike(productId)
    }
}
