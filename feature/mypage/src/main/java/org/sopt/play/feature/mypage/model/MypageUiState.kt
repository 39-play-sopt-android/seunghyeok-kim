package org.sopt.play.feature.mypage.model

import androidx.compose.runtime.Immutable

@Immutable
sealed interface MypageUiState {
    data object Loading: MypageUiState
    data object Mypage: MypageUiState
}

sealed interface MypageIntent {

}