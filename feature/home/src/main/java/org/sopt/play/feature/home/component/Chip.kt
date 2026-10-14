package org.sopt.play.feature.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.core.designsystem.R
import org.sopt.play.core.ui.ext.nullIndication
import org.sopt.play.designsystem.theme.PlaySoptPreviewTheme
import org.sopt.play.designsystem.theme.PlaySoptTheme
import org.sopt.play.feature.home.model.Product
import org.sopt.play.feature.home.ui.calculateDiscountRate
import org.sopt.play.feature.home.ui.toDecimalFormat

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
                if(isSelect) PlaySoptTheme.color.gray5 else PlaySoptTheme.color.gray3,
                RoundedCornerShape(6.dp)
            )
            .padding(horizontal = 12.dp)
            .padding(vertical = 8.dp)
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
fun ChipPreview(){
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