package org.sopt.play.feature.home.model

import androidx.compose.runtime.Immutable

@Immutable
sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Default(
        val selectedMenu: String = "홈",
        val selectedCategory: String = "전체",
        val likedProduct: Set<Int> = emptySet(),
    ) : HomeUiState
}

sealed interface HomeIntent {
    data object ClickSearch : HomeIntent
    data class SelectMenu(val menu: String) : HomeIntent
    data class SelectCategory(val category: String) : HomeIntent
    data class ToggleLike(val productId: Int) : HomeIntent
    data class ClickProduct(val productId: Int) : HomeIntent
}