package org.sopt.play.designsystem.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PlaySoptTheme(
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 430.dp)
                .align(Alignment.Center)
                .background(PlaySoptColor.white),
            contentAlignment = Alignment.Center
        ) {
            val colors: PlaySoptColor = PlaySoptColor
            val typography = playSoptTypography

            CompositionLocalProvider(
                LocalColor provides colors,
                LocalTypography provides typography
            ) {
                content()
            }
        }
    }
}

object PlaySoptTheme {
    val color: PlaySoptColor
        @Composable
        get() = LocalColor.current

    val typography: PlaySoptTypography
        @Composable
        get() = LocalTypography.current
}
