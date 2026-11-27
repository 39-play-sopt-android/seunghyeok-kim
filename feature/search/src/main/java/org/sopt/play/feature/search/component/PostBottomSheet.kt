package org.sopt.play.feature.search.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.core.designsystem.component.PlaySoptBottomSheet
import org.sopt.play.core.designsystem.component.PlaySoptButton
import org.sopt.play.core.designsystem.component.PlaySoptTextField
import org.sopt.play.core.designsystem.component.TextFieldStatus
import org.sopt.play.core.designsystem.theme.PlaySoptPreviewTheme

@Composable
fun PostBottomSheet(
    onDismiss: () -> Unit,
    onSubmit: (String, String) -> Unit = { _, _ -> },
    modifier: Modifier = Modifier,
) {
    val titleState = rememberTextFieldState()
    val contentState = rememberTextFieldState()

    PlaySoptBottomSheet(
        onDismiss = onDismiss,
        modifier = modifier
    ) {
        PostBottomSheetContent(
            titleState = titleState,
            contentState = contentState,
            onDismiss = onDismiss,
            onSubmit = onSubmit
        )
    }
}

@Composable
fun PostBottomSheetContent(
    titleState: TextFieldState,
    contentState: TextFieldState,
    onDismiss: () -> Unit,
    onSubmit: (String, String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val titleStatus: TextFieldStatus = when {
        titleState.text.isEmpty() -> TextFieldStatus.Default
        titleState.text.length > 20 -> TextFieldStatus.Error("제목은 최대 20자까지 가능합니다.")
        else -> TextFieldStatus.Valid
    }

    val contentStatus: TextFieldStatus = when {
        contentState.text.isEmpty() -> TextFieldStatus.Default
        contentState.text.length > 1000 -> TextFieldStatus.Error("본문은 최대 1000자까지 가능합니다.")
        else -> TextFieldStatus.Valid
    }

    val isSubmitEnabled = titleStatus is TextFieldStatus.Valid && contentStatus is TextFieldStatus.Valid

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            PlaySoptTextField(
                title = "제목",
                state = titleState,
                hint = "제목을 입력하세요.",
                status = titleStatus,
                errorMessage = "제목은 최대 20자까지 가능합니다."
            )

            PlaySoptTextField(
                title = "본문",
                state = contentState,
                hint = "본문을 입력하세요.",
                status = contentStatus,
                errorMessage = "본문은 최대 1000자까지 가능합니다.",
                maxLine = 3 //너무 길어지면 바텀 시트 영역이 커질까봐 추가
            )
        }

        PlaySoptButton(
            text = "완료",
            enabled = isSubmitEnabled,
            onClick = {
                if (isSubmitEnabled) {
                    onSubmit(titleState.text.toString(), contentState.text.toString())
                    onDismiss()
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PostBottomSheetPreview() {
    PlaySoptPreviewTheme {
        PostBottomSheetContent(
            titleState = rememberTextFieldState(),
            contentState = rememberTextFieldState(),
            onDismiss = {},
            onSubmit = { _, _ -> }
        )
    }
}
