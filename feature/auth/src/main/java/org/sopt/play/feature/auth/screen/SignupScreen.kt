package org.sopt.play.feature.auth.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun SignupScreen(
    onSignUpClick: () -> Unit = {},
    backClick: () -> Unit = {}
){
    val focusManager = LocalFocusManager.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val emailStatus = when {
        email.isEmpty() -> 1
        !email.contains("@") -> 4
        else -> 2
    }

    val passwordStatus = when {
        password.isEmpty() -> 1
        password.length < 6 -> 4
        else -> 2
    }

    val loginStatus = emailStatus == 2 && passwordStatus == 2

    Column(
        modifier = Modifier
            .playSoptDefault(PlaySoptTheme.color.white)
            .padding(16.dp, 60.dp, 16.dp, 0.dp),
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        Text("이메일로 로그인하기", style = PlaySoptTheme.typography.b28)
        Column(
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ){
            PlaySoptTextField(
                title = "이메일 주소",
                value = email,
                onValueChange = { email = it },
                hint = "abc@email.com",
                status = emailStatus,
                errorMessage = "올바른 이메일을 입력해주세요.",
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
            PlaySoptTextField(
                title = "비밀번호",
                value = password,
                hint = "6자 이상의 비밀번호",
                onValueChange = { password = it },
                status = passwordStatus,
                errorMessage = "6자 이상의 비밀번호를 입력해주세요.",
                type = "password",
                onDone = {
                    if (loginStatus) onSignUpClick()
                }
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            PlaySoptButton(
                text = "로그인",
                onClick = {
                    onSignUpClick()
                },
                enabled = loginStatus
            )
        }
    }
}