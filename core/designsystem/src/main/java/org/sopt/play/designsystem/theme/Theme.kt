package org.sopt.play.designsystem.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

@Composable
fun PlaySoptPreviewTheme(
    content: @Composable () -> Unit
) {
    val colors: PlaySoptColor = PlaySoptColor
    val typography = playSoptTypography

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .border(
                1.dp,
                Color(0xFF8A38F5),
                RoundedCornerShape(5.dp)
            )
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        CompositionLocalProvider(
            LocalColor provides colors,
            LocalTypography provides typography
        ) {
            content()
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
