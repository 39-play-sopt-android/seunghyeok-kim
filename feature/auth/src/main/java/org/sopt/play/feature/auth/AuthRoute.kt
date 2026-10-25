package org.sopt.play.feature.auth

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import org.sopt.play.feature.auth.model.AuthIntent
import org.sopt.play.feature.auth.model.AuthUiState.Login
import org.sopt.play.feature.auth.model.AuthUiState.Register
import org.sopt.play.feature.auth.screen.LoginScreen
import org.sopt.play.feature.auth.screen.RegisterScreen

@Composable
fun AuthRoute(
    viewModel: AuthViewModel = koinViewModel(),
    onBackClick: () -> Unit,
    onNavToHome: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(viewModel.event) {
        viewModel.event.collect { event ->
            when (event) {
                is AuthEvent.NavToHome -> onNavToHome()
                is AuthEvent.ExitAuth -> onBackClick()
                is AuthEvent.ShowToast -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    when (uiState) {
        is Login -> LoginScreen(
            navToSignUp = { viewModel.onIntent(AuthIntent.NavToRegister) },
            onLoginClick = { id, pw -> viewModel.login(id, pw) }
        )
        is Register -> {
            BackHandler { viewModel.onIntent(AuthIntent.NavToBack) }
            RegisterScreen( onSignUpClick = { name, email, pw -> viewModel.register(name, email, pw) })
        }
        else -> {
            // Loading, Success, Error
        }
    }
}
