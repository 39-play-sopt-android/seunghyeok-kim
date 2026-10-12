package org.sopt.play.feature.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.sopt.play.designsystem.theme.PlaySoptTheme
import org.sopt.play.feature.auth.screen.LoginScreen

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlaySoptTheme {
                LoginScreen(
                    onLoginClick = {
                        val resultIntent = Intent().apply {
                            putExtra("is_logged_in", true)
                        }
                        setResult(RESULT_OK, resultIntent)
                        finish()
                    },
                    navToSignUpClick = {
                        val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                        startActivity(intent)
                    }
                )
            }
        }
    }
}