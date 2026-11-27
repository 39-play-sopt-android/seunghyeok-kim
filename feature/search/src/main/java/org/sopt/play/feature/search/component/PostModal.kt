package org.sopt.play.feature.search.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.core.designsystem.component.ButtonStatus
import org.sopt.play.core.designsystem.component.PlaySoptButton
import org.sopt.play.core.designsystem.component.PlaySoptModal
import org.sopt.play.core.designsystem.theme.PlaySoptPreviewTheme
import org.sopt.play.core.designsystem.theme.PlaySoptTheme

@Composable
fun PostModal(
    onDismiss: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    PlaySoptModal(
        onDismiss = onDismiss,
        modifier = modifier
    ) {
        PostModalContent(
            onDismiss = onDismiss,
            onDeleteClick = onDeleteClick
        )
    }
}

@Composable
private fun PostModalContent(
    onDismiss: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "게시글을 삭제할까요?",
            style = PlaySoptTheme.typography.m18,
            color = PlaySoptTheme.color.gray6,
            textAlign = TextAlign.Center
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            PlaySoptButton(
                text = "취소",
                status = ButtonStatus.EnabledWhite,
                modifier = Modifier.weight(1f),
                onClick = onDismiss
            )
            PlaySoptButton(
                text = "삭제",
                status = ButtonStatus.EnabledBlack,
                modifier = Modifier.weight(1f),
                onClick = {
                    onDeleteClick()
                    onDismiss()
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PostModalPreview() {
    PlaySoptPreviewTheme {
        PostModalContent(
            onDismiss = {},
            onDeleteClick = {}
        )
    }
}
