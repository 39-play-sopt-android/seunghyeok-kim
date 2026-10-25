package org.sopt.play.feature.like.model

import androidx.compose.runtime.Immutable

@Immutable
sealed interface LikeUiState {
    data object Loading: LikeUiState
    data object Like: LikeUiState
}

sealed interface LikeIntent {

}