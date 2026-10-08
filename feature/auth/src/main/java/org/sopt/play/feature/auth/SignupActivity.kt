package org.sopt.play.feature.auth

import android.R.attr.password
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.core.ui.ext.playSoptDefault
import org.sopt.play.designsystem.component.PlaySoptButton
import org.sopt.play.designsystem.component.PlaySoptTextField
import org.sopt.play.designsystem.theme.PlaySoptTheme
import org.sopt.play.feature.auth.screen.LoginScreen
import org.sopt.play.feature.auth.screen.SignupScreen

class SignupActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlaySoptTheme {
                SignupScreen (

                )
            }
        }
    }
}