package org.sopt.play.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.core.ui.ext.nullIndication
import org.sopt.play.core.designsystem.theme.PlaySoptPreviewTheme
import org.sopt.play.core.designsystem.theme.PlaySoptTheme

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    title: String,
    isSelect: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .background(if(isSelect) PlaySoptTheme.color.gray5 else PlaySoptTheme.color.white)
            .nullIndication(onClick = onClick)
            .border(
                1.dp,
                if(isSelect) PlaySoptTheme.color.gray5 else PlaySoptTheme.color.gray2,
                RoundedCornerShape(6.dp)
            )
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ){
        Text(
            text = title,
            style = PlaySoptTheme.typography.m14,
            color = if(isSelect) PlaySoptTheme.color.white else PlaySoptTheme.color.gray5
        )
    }
}

@Preview
@Composable
private fun ChipPreview(){
    PlaySoptPreviewTheme {
        Chip(
            title = "칩",
            isSelect = false,
            onClick = {}
        )
        Chip(
            title = "칩",
            isSelect = true,
            onClick = {}
        )
    }
}