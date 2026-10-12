package org.sopt.play

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.sopt.play.designsystem.theme.PlaySoptTheme
import org.sopt.play.feature.auth.LoginActivity

class MainActivity : ComponentActivity() {

    private val loginLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val isLoggedIn = result.data?.getBooleanExtra(EXTRA_IS_LOGGED_IN, false) ?: false
            if (!isLoggedIn) {
                // 로그인 실패 -> 로그인 화면으로 이동
                moveToLogin()
            } else {
                // 로그인 성공 -> MainActivity 시작
                MainScreen()
            }
        } else {
            // 이외의 오류 -> 종료
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isLoggedIn = intent.getBooleanExtra(EXTRA_IS_LOGGED_IN, false)
        if (!isLoggedIn) {
            moveToLogin()
        } else {
            MainScreen()
        }
    }

    private fun moveToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        loginLauncher.launch(intent)
    }

    private fun MainScreen() {
        setContent {
            PlaySoptTheme {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "메인 화면",
                        style = PlaySoptTheme.typography.b28
                    )
                }
            }
        }
    }

    companion object {
        const val EXTRA_IS_LOGGED_IN = "is_logged_in"
    }
}