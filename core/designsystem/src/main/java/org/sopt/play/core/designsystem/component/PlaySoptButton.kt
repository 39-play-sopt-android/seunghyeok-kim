package org.sopt.play.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.core.designsystem.theme.PlaySoptPreviewTheme
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.core.ui.ext.nullIndication

sealed class ButtonStatus {
    data object EnabledBlack : ButtonStatus()
    data object EnabledWhite : ButtonStatus()
    data object Disabled : ButtonStatus()
}

@Composable
fun PlaySoptButton(
    text: String,
    modifier: Modifier = Modifier.fillMaxWidth(),
    enabled: Boolean = true,
    status: ButtonStatus = if (enabled) ButtonStatus.EnabledBlack else ButtonStatus.Disabled,
    textStyle: TextStyle = PlaySoptTheme.typography.sb14,
    onClick: () -> Unit
) {
    val backgroundColor = when (status) {
        ButtonStatus.EnabledBlack -> PlaySoptTheme.color.black
        ButtonStatus.EnabledWhite -> PlaySoptTheme.color.gray1
        ButtonStatus.Disabled -> PlaySoptTheme.color.gray1
    }

    val contentColor = when (status) {
        ButtonStatus.EnabledBlack -> PlaySoptTheme.color.gray1
        ButtonStatus.EnabledWhite -> PlaySoptTheme.color.gray5
        ButtonStatus.Disabled -> PlaySoptTheme.color.gray3
    }

    val isClickable = status != ButtonStatus.Disabled

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(100.dp))
            .background(color = backgroundColor)
            .nullIndication(
                enabled = isClickable,
                onClick = onClick
            )
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

@Preview(showBackground = true)
@Composable
private fun PlaySoptButtonPreview() {
    PlaySoptPreviewTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            PlaySoptButton(
                text = "로그인",
                status = ButtonStatus.Disabled,
                onClick = {}
            )
            PlaySoptButton(
                text = "로그인",
                status = ButtonStatus.EnabledBlack,
                onClick = {}
            )
            PlaySoptButton(
                text = "로그인",
                status = ButtonStatus.EnabledWhite,
                onClick = {}
            )
        }
    }
}
