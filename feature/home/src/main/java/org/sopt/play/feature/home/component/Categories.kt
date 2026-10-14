package org.sopt.play.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.designsystem.theme.PlaySoptPreviewTheme
import org.sopt.play.designsystem.theme.PlaySoptTheme

@Composable
fun Categories(
    modifier: Modifier = Modifier,
    title: List<String>,
    isSelect: String,
    onItemClick: () -> Unit
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ){
        items(title.size){
            Chip(
                title = title[it],
                isSelect = title[it] == isSelect,
                onClick = onItemClick
            )
        }
    }
}

@Preview
@Composable
fun Categories(){
    PlaySoptPreviewTheme {
        Categories(
            title = listOf("전체", "상의", "팬츠", "원피스", "아우터", "스커트", "슈즈", "가방", "이너웨어/잠옷", "폰꾸미기"),
            isSelect = "전체",
            onItemClick = {}
        )
    }
}