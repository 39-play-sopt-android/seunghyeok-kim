package org.sopt.play.feature.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.play.feature.auth.model.AuthIntent
import org.sopt.play.feature.auth.model.AuthUiState

sealed class AuthEvent{
    data object NavToHome: AuthEvent()
    data object ExitAuth: AuthEvent()
}

class AuthViewModel: ViewModel(){
    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Login)
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<AuthEvent>()
    val event = _event.asSharedFlow()

    private val backStack = mutableListOf<AuthUiState>()

    private fun navTo(next: AuthUiState) {
        if (_uiState.value == next) return
        backStack.add(_uiState.value)
        _uiState.value = next
    }


    private fun navigateBack() {
        val previous = backStack.removeLastOrNull()
        if (previous != null) {
            _uiState.value = previous
        } else {
            viewModelScope.launch {
                _event.emit(AuthEvent.ExitAuth)
            }
        }
    }

    fun onIntent(intent: AuthIntent){
        when(intent){
            is AuthIntent.NavToRegister -> navTo(AuthUiState.Register)
            is AuthIntent.NavToHome -> navToHome()
            is AuthIntent.NavToBack -> navigateBack()
        }
    }

    private fun navToHome() {
        viewModelScope.launch {
            _event.emit(AuthEvent.NavToHome)
        }
    }
}