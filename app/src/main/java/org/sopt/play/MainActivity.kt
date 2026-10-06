package org.sopt.play

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import org.sopt.play.feature.auth.LoginActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //추후 로그인 여부 확인 후 LoginActivity로 넘어가도록 설계 필요
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}