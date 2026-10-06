package org.sopt.play.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import org.sopt.play.designsystem.theme.PlaySoptTheme

@Composable
fun PlaySoptTextField(
    modifier: Modifier = Modifier,
    title: String? = null,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String? = null,
    status: Int? = null, //1 = default, 2 = default2, 3 = focused, 4 = error
    errorMassage: String? = null,
    onDone: (() -> Unit)? = null,
    maxLine: Int = 1
){
    val isNotEmpty = value.isNotEmpty()
    val keyboardController = LocalSoftwareKeyboardController.current

    val borderColor = when(status){
        1 or 2 -> { PlaySoptTheme.color.gray2 }
        3 -> PlaySoptTheme.color.gray5
        4 -> PlaySoptTheme.color.red
        else -> PlaySoptTheme.color.gray2
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ){
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = title ?: "",
            style = PlaySoptTheme.typography.sb14,
            color = PlaySoptTheme.color.gray6
        )

        BasicTextField(
            modifier = modifier.fillMaxWidth(),
            value = value,
            onValueChange = {
                onValueChange(it)
            },
            maxLines = maxLine,
            singleLine = maxLine == 1,
            textStyle = PlaySoptTheme.typography.m18,
            keyboardOptions = KeyboardOptions(
                imeAction = if (maxLine > 1) ImeAction.Default else ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    onDone?.invoke()
                }
            ),
            cursorBrush = SolidColor(PlaySoptTheme.color.gray5),
            decorationBox = { innerTextField ->
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
                        .padding(16.dp, 10.dp),
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
        if(status == 4){
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = errorMassage ?: "오류가 있습니다",
                style = PlaySoptTheme.typography.m14,
                color = PlaySoptTheme.color.red
            )
        }
    }
}