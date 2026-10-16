package org.sopt.play.feature.auth

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.play.feature.auth.model.AuthIntent
import org.sopt.play.feature.auth.model.AuthUiState
import org.sopt.play.feature.auth.screen.LoginScreen
import org.sopt.play.feature.auth.screen.RegisterScreen

@Composable
fun AuthRoute(
    viewModel: AuthViewModel = viewModel(),
    onBackClick: () -> Unit,
    onNavToHome: () -> Unit,
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.event) {
        viewModel.event.collect { event ->
            when(event) {
                is AuthEvent.NavToHome -> onNavToHome()
                is AuthEvent.ExitAuth -> onBackClick()
            }
        }
    }

    when(uiState){
        is AuthUiState.Login -> LoginScreen(
            navToSignUp = { viewModel.onIntent(AuthIntent.NavToRegister) },
            onLoginClick = { viewModel.onIntent(AuthIntent.NavToHome) }
        )
        is AuthUiState.Register -> RegisterScreen(
            onSignUpClick = { viewModel.onIntent(AuthIntent.NavToBack) }
        )
    }
}