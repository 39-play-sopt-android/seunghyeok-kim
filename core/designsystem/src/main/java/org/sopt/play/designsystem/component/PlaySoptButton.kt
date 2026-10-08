package org.sopt.play.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import org.sopt.play.designsystem.theme.PlaySoptTheme

@Composable
fun PlaySoptButton(
    modifier: Modifier = Modifier.fillMaxWidth(),
    text: String,
    enabled: Boolean = true,
    textStyle: TextStyle = PlaySoptTheme.typography.sb14,
    backgroundColor: Color = if (enabled) PlaySoptTheme.color.black else PlaySoptTheme.color.gray1,
    contentColor: Color = if (enabled) PlaySoptTheme.color.gray1 else PlaySoptTheme.color.gray3,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor,
            disabledContainerColor =backgroundColor,
            disabledContentColor = contentColor
        ),
        shape = RoundedCornerShape(100.dp),
        enabled = enabled,
        contentPadding = PaddingValues(0.dp),
        onClick = onClick
    ) {
        Box(
            modifier = Modifier
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
}