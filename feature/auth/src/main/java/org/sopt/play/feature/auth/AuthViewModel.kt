package org.sopt.play.feature.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.sopt.play.core.domain.repository.AuthRepository
import org.sopt.play.core.domain.repository.LoginResult
import org.sopt.play.feature.auth.model.AuthIntent
import org.sopt.play.feature.auth.model.AuthUiState

sealed class AuthEvent {
    data object NavToHome : AuthEvent()
    data object ExitAuth : AuthEvent()
    data class ShowToast(val message: String) : AuthEvent()
}

class AuthViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Loading)
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<AuthEvent>()
    val event = _event.asSharedFlow()

    private val backStack = mutableListOf<AuthUiState>()

    init {
        // ViewModel 초기화 시 자동 로그인 검사 실행
        checkAutoLogin()
    }

    private fun checkAutoLogin() {
        authRepository.isLoggedInFlow
            .onEach { isLoggedIn ->
                if (isLoggedIn) {
                    _uiState.value = AuthUiState.Success
                } else {
                    _uiState.value = AuthUiState.Login
                }
            }
            .launchIn(viewModelScope) // ViewModel 생명주기에 맞춰 코루틴 실행
    }

    fun login(email: String, pw: String) {
        viewModelScope.launch {
            when (val result = authRepository.login(email, pw)) {
                is LoginResult.Success -> {
                    _uiState.value = AuthUiState.Success
                    _event.emit(AuthEvent.NavToHome)
                }
                is LoginResult.UserNotFound -> {
                    _event.emit(AuthEvent.ShowToast("존재하지 않는 유저입니다."))
                }
                is LoginResult.InvalidPassword -> {
                    _event.emit(AuthEvent.ShowToast("비밀번호가 일치하지 않습니다."))
                }
            }
        }
    }

    fun register(name: String, email: String, pw: String) {
        viewModelScope.launch {
            authRepository.register(name, email, pw)
            _event.emit(AuthEvent.ShowToast("회원가입이 완료되었습니다."))
            navTo(AuthUiState.Login)
        }
    }

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

    fun onIntent(intent: AuthIntent) {
        when (intent) {
            is AuthIntent.NavToRegister -> navTo(AuthUiState.Register)
            is AuthIntent.NavToHome -> {
                viewModelScope.launch {
                    _event.emit(AuthEvent.NavToHome)
                }
            }
            is AuthIntent.NavToBack -> navigateBack()
        }
    }
}
