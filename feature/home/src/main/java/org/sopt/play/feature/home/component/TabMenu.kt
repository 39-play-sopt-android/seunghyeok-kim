package org.sopt.play.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.core.ui.ext.nullIndication
import org.sopt.play.core.designsystem.theme.PlaySoptPreviewTheme
import org.sopt.play.core.designsystem.theme.PlaySoptTheme

@Composable
fun TabMenu(
    modifier: Modifier = Modifier,
    title: String,
    isSelect: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .nullIndication(onClick = onClick)
            .padding(horizontal = 16.dp)
            .padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
        modifier = Modifier
            .size(4.dp)
            .clip(CircleShape)
            .background(if(isSelect) PlaySoptTheme.color.black else Color.Transparent)
        )
        Text(
            text = title,
            style = PlaySoptTheme.typography.m18,
            color = if (isSelect) PlaySoptTheme.color.black else PlaySoptTheme.color.gray3
        )
    }
}

@Preview
@Composable
private fun TabMenuPreview() {
    PlaySoptPreviewTheme {
        Row {
            TabMenu(
                title = "메뉴",
                isSelect = true,
                onClick = {}
            )
            TabMenu(
                title = "메뉴",
                isSelect = false,
                onClick = {}
            )
        }
    }
}