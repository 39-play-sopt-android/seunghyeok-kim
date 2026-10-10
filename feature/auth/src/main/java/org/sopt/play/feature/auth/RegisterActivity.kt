package org.sopt.play.feature.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.sopt.play.designsystem.theme.PlaySoptTheme
import org.sopt.play.feature.auth.screen.RegisterScreen

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlaySoptTheme {
                RegisterScreen(
                    onSignUpClick = {
                        val intent = Intent().apply {
                            setClassName(this@RegisterActivity, "org.sopt.play.MainActivity")
                            putExtra("is_logged_in", true)
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        }
                        startActivity(intent)
                    }
                )
            }
        }
    }
}