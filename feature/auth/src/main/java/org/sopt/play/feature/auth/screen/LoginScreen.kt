package org.sopt.play.feature.auth.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import org.sopt.play.core.ui.ext.isValidEmail
import org.sopt.play.core.ui.ext.nullIndication
import org.sopt.play.core.ui.ext.playSoptDefault
import org.sopt.play.designsystem.component.PlaySoptButton
import org.sopt.play.designsystem.component.PlaySoptTextField
import org.sopt.play.designsystem.component.TextFieldStatus
import org.sopt.play.designsystem.theme.PlaySoptTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navToSignUp: () -> Unit,
    onLoginClick: () -> Unit
){
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    val emailState = rememberTextFieldState()
    val passwordState = rememberTextFieldState()

    val emailStatus: TextFieldStatus = when {
        emailState.text.isEmpty() -> TextFieldStatus.Default
        !emailState.text.toString().isValidEmail() -> TextFieldStatus.Error("올바른 이메일을 입력해주세요.")
        else -> TextFieldStatus.Valid
    }


    val passwordStatus: TextFieldStatus = when {
        passwordState.text.isEmpty() -> TextFieldStatus.Default
        passwordState.text.length < 6 -> TextFieldStatus.Error("6자 이상의 비밀번호를 입력해주세요.")
        else -> TextFieldStatus.Valid
    }



    val isLoginEnabled = emailStatus is TextFieldStatus.Valid &&
            passwordStatus is TextFieldStatus.Valid

    Column(
        modifier = modifier
            .playSoptDefault(PlaySoptTheme.color.white)
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp)
            .padding(top = 60.dp, bottom = 40.dp),
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        Text("이메일로 로그인하기", style = PlaySoptTheme.typography.b28)
        Column(
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ){
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
                errorMessage = "비밀번호는 6자 이상 입력해주세요.",
                type = "password"
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            PlaySoptButton(
                text = "로그인",
                onClick = {
                    onLoginClick()
                },
                enabled = isLoginEnabled
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    "아직 계정이 없으신가요?",
                    style = PlaySoptTheme.typography.m14,
                    color = PlaySoptTheme.color.gray3
                )
                Text(
                    "회원가입",
                    style = PlaySoptTheme.typography.m14,
                    color = PlaySoptTheme.color.gray6,
                    modifier = Modifier.nullIndication(onClick = navToSignUp)
                )
            }
        }
    }
}