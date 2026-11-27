package org.sopt.play.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import org.sopt.play.core.designsystem.theme.PlaySoptTheme

@Composable
fun PlaySoptModal(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    properties: DialogProperties = DialogProperties(),
    content: @Composable () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = properties
    ) {
        Box(
            modifier = modifier
                .width(288.dp)
                .wrapContentHeight()
                .clip(RoundedCornerShape(12.dp))
                .background(PlaySoptTheme.color.white)
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}
