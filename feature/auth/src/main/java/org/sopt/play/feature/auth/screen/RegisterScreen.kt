package org.sopt.play.feature.auth.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import org.sopt.play.core.ui.ext.playSoptDefault
import org.sopt.play.designsystem.component.PlaySoptButton
import org.sopt.play.designsystem.component.PlaySoptTextField
import org.sopt.play.designsystem.theme.PlaySoptTheme

@Composable
fun RegisterScreen(
    onSignUpClick: () -> Unit = {}
){
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    val nameState = rememberTextFieldState()
    val emailState = rememberTextFieldState()
    val passwordState = rememberTextFieldState()
    val passwordCheckState = rememberTextFieldState()

    val nameStatus = when {
        nameState.text.isEmpty() -> 1
        else -> 2
    }

    val emailStatus = when {
        emailState.text.isEmpty() -> 1
        !emailState.text.contains("@") -> 4
        else -> 2
    }

    val passwordStatus = when {
        passwordState.text.isEmpty() -> 1
        passwordState.text.length < 6 -> 4
        else -> 2
    }

    val passwordCheckStatus = when {
        passwordCheckState.text.isEmpty() -> 1
        passwordCheckState.text != passwordState.text -> 4
        else -> 2
    }

    val signUpStatus = nameStatus == 2 && emailStatus == 2 && passwordStatus == 2 && passwordCheckStatus == 2

    Column(
        modifier = Modifier
            .playSoptDefault(PlaySoptTheme.color.white)
            .verticalScroll(scrollState)
            .padding(16.dp, 60.dp, 16.dp, 40.dp),
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        Text("이메일로 회원가입", style = PlaySoptTheme.typography.b28)
        Column(
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ){
            PlaySoptTextField(
                title = "이름",
                state = nameState,
                hint = "홍길동",
                status = nameStatus,
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
            PlaySoptTextField(
                title = "이메일 주소",
                state = emailState,
                hint = "abc@email.com",
                status = emailStatus,
                errorMessage = "올바른 이메일을 입력해주세요.",
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
            PlaySoptTextField(
                title = "비밀번호",
                state = passwordState,
                hint = "6자 이상의 비밀번호",
                status = passwordStatus,
                errorMessage = "6자 이상의 비밀번호를 입력해주세요.",
                type = "password",
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
            PlaySoptTextField(
                title = "비밀번호 확인",
                state = passwordCheckState,
                hint = "6자 이상의 비밀번호",
                status = passwordCheckStatus,
                errorMessage = "비밀번호가 일치하지 않습니다.",
                type = "password"
            )
        }
        PlaySoptButton(
            text = "회원가입",
            onClick = {
                onSignUpClick()
            },
            enabled = signUpStatus
        )
    }
}