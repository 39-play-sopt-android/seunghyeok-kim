package org.sopt.play.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.play.designsystem.theme.PlaySoptPreviewTheme
import org.sopt.play.designsystem.theme.PlaySoptTheme

@Composable
fun Menus(
    modifier: Modifier = Modifier,
    title: List<String>,
    isSelect: String,
    onItemClick: () -> Unit
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .background(PlaySoptTheme.color.white)
    ){
        items(title.size){
            TabMenu(
                title = title[it],
                isSelect = title[it] == isSelect,
                onClick = onItemClick
            )
        }
    }
}

@Preview
@Composable
fun MenusPreview(){
    PlaySoptPreviewTheme {
        Menus(
            title = listOf("2배 적립", "홈", "셀렉티드", "브랜드", "뷰티", "라이프", "랭킹"),
            isSelect = "홈",
            onItemClick = {}
        )
    }
}