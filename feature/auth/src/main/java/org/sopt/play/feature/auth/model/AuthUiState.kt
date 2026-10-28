package org.sopt.play.feature.auth.model

import androidx.compose.runtime.Immutable

@Immutable
sealed interface AuthUiState {
    data object Loading : AuthUiState
    data object Success : AuthUiState
    data class Error(val message: String) : AuthUiState

    data object Login : AuthUiState
    data object Register : AuthUiState
}

sealed interface AuthIntent {
    data object NavToBack : AuthIntent
    data object NavToHome : AuthIntent
    data object NavToRegister : AuthIntent
}
