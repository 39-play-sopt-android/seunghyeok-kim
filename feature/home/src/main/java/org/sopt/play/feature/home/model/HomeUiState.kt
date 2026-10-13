package org.sopt.play.feature.home.model

import androidx.compose.runtime.Immutable

@Immutable
sealed interface HomeUiState {
    data object Loading: HomeUiState
    data object Home: HomeUiState
}

sealed interface HomeIntent {

}