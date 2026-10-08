package org.sopt.play.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import org.sopt.play.designsystem.theme.PlaySoptTheme

@Composable
fun PlaySoptButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    textStyle: TextStyle = PlaySoptTheme.typography.sb14,
    backgroundColor: Color = if (enabled) PlaySoptTheme.color.black else PlaySoptTheme.color.gray1,
    contentColor: Color = if (enabled) PlaySoptTheme.color.gray1 else PlaySoptTheme.color.gray3,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(100.dp)
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null, // 리플 효과 완전 제거
                enabled = enabled
            ) {
                onClick()
            }
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = textStyle,
            color = contentColor
        )
    }
}