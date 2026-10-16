package org.sopt.play

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.sopt.play.designsystem.theme.PlaySoptTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlaySoptTheme {
                AppNavHost()
            }
        }
    }
}