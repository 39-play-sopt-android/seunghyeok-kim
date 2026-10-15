package org.sopt.play.designsystem.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
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
    val colors: PlaySoptColor = PlaySoptColor
    val typography = playSoptTypography

    CompositionLocalProvider(
        LocalColor provides colors,
        LocalTypography provides typography
    ) {
        content()
    }
}

@Composable
fun PlaySoptPreviewTheme(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    val colors: PlaySoptColor = PlaySoptColor
    val typography = playSoptTypography

    CompositionLocalProvider(
        LocalColor provides colors,
        LocalTypography provides typography
    ) {
        Row(
            modifier = modifier
                .clip(RoundedCornerShape(5.dp))
                .background(PlaySoptColor.white)
                .border(
                    1.dp,
                    Color(0xFF8A38F5),
                    RoundedCornerShape(5.dp)
                )
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            content = content
        )
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