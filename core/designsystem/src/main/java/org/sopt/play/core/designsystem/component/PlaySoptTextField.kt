package org.sopt.play.designsystem.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import org.sopt.play.core.designsystem.theme.PlaySoptTheme

sealed class TextFieldStatus {
    data object Default : TextFieldStatus()
    data object Valid : TextFieldStatus()
    data object Focused: TextFieldStatus()
    data class Error(val errorMessage: String): TextFieldStatus()
}

@Composable
fun PlaySoptTextField(
    modifier: Modifier = Modifier,
    title: String,
    state: TextFieldState,
    hint: String,
    status: TextFieldStatus? = null,
    errorMessage: String? = null,
    onDone: (() -> Unit)? = null,
    onNext: (() -> Unit)? = null,
    imeAction: ImeAction? = null,
    type: String? = null,
    maxLine: Int = 1
) {
    val isNotEmpty = state.text.isNotEmpty()
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    val currentStatus: TextFieldStatus = when {
        status is TextFieldStatus.Error -> status
        isFocused -> TextFieldStatus.Focused
        status != null -> status
        isNotEmpty -> TextFieldStatus.Valid
        else -> TextFieldStatus.Default
    }

    val borderColor = when (currentStatus) {
        is TextFieldStatus.Default, is TextFieldStatus.Valid -> PlaySoptTheme.color.gray2
        is TextFieldStatus.Focused -> PlaySoptTheme.color.gray5
        is TextFieldStatus.Error -> PlaySoptTheme.color.red
    }

    val effectiveImeAction = when {
        maxLine > 1 -> ImeAction.Default
        imeAction != null -> imeAction
        onNext != null -> ImeAction.Next
        else -> ImeAction.Done
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        if (!title.isNullOrEmpty()) {
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = title,
                style = PlaySoptTheme.typography.sb16,
                color = PlaySoptTheme.color.gray6
            )
        }

        BasicTextField(
            modifier = Modifier.fillMaxWidth(),
            state = state,
            lineLimits = if (maxLine == 1) TextFieldLineLimits.SingleLine else TextFieldLineLimits.MultiLine(maxHeightInLines = maxLine),
            textStyle = PlaySoptTheme.typography.m18,
            interactionSource = interactionSource,
            outputTransformation = if (type == "password") OutputTransformation { replace(0, length, "\u2022".repeat(length)) } else null,
            keyboardOptions = KeyboardOptions(
                imeAction = effectiveImeAction
            ),
            onKeyboardAction = {
                if (effectiveImeAction == ImeAction.Next) {
                    if (onNext != null) {
                        onNext()
                    } else {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                } else if (effectiveImeAction == ImeAction.Done) {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                    onDone?.invoke()
                }
            },
            cursorBrush = SolidColor(PlaySoptTheme.color.gray5),
            decorator = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = PlaySoptTheme.color.white,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .border(
                            width = 2.dp,
                            color = borderColor,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = if (maxLine > 1) Alignment.Top else Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = if (maxLine > 1) Alignment.TopStart else Alignment.CenterStart
                    ) {
                        if (!isNotEmpty) {
                            hint?.let { h ->
                                Text(
                                    text = h,
                                    style = PlaySoptTheme.typography.m18,
                                    color = PlaySoptTheme.color.gray2
                                )
                            }
                        }
                        innerTextField()
                    }
                }
            }
        )
        AnimatedVisibility(
            visible = currentStatus is TextFieldStatus.Error,
            enter = fadeIn(
                animationSpec = tween(
                    durationMillis = 200,
                    easing = FastOutSlowInEasing
                )
            ) + expandVertically(
                animationSpec = tween(
                    durationMillis = 200,
                    easing = FastOutSlowInEasing
                ),
                expandFrom = Alignment.Top
            ),
            exit = fadeOut(
                animationSpec = tween(
                    durationMillis = 200
                )
            ) + shrinkVertically(
                animationSpec = tween(
                    durationMillis = 200
                ),
                shrinkTowards = Alignment.Top
            )
        ) {
            Text(
                modifier = Modifier.padding(start = 8.dp, top = 4.dp),
                text = errorMessage ?: "오류가 있습니다",
                style = PlaySoptTheme.typography.m14,
                color = PlaySoptTheme.color.red
            )
        }
    }
}