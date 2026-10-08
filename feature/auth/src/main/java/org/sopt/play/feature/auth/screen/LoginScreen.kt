package org.sopt.play.feature.auth.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import org.sopt.play.core.ui.ext.playSoptDefault
import org.sopt.play.designsystem.component.PlaySoptButton
import org.sopt.play.designsystem.component.PlaySoptTextField
import org.sopt.play.designsystem.theme.PlaySoptTheme

@Composable
fun LoginScreen(
    navToSignUpClick: () -> Unit = {},
    onLoginClick: () -> Unit = {}
){
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    val emailState = rememberTextFieldState()
    val passwordState = rememberTextFieldState()

    val emailText = emailState.text.toString()
    val passwordText = passwordState.text.toString()

    val emailStatus = when {
        emailText.isEmpty() -> 1
        !emailText.contains("@") -> 4
        else -> 2
    }

    val passwordStatus = when {
        passwordText.isEmpty() -> 1
        passwordText.length < 6 -> 4
        else -> 2
    }

    val loginStatus = emailStatus == 2 && passwordStatus == 2

    Column(
        modifier = Modifier
            .playSoptDefault(PlaySoptTheme.color.white)
            .verticalScroll(scrollState)
            .padding(16.dp, 60.dp, 16.dp, 40.dp),
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
                enabled = loginStatus
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
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {
                            navToSignUpClick()
                        }
                    )
                )
            }
        }
    }
}