package org.sopt.play

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.sopt.play.designsystem.theme.PlaySoptTheme
import org.sopt.play.feature.auth.LoginActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isLoggedIn = intent.getBooleanExtra(EXTRA_IS_LOGGED_IN, false)
        if (!isLoggedIn) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

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