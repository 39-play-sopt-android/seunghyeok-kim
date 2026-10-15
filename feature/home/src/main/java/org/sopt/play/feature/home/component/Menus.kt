package org.sopt.play.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.play.designsystem.theme.PlaySoptPreviewTheme
import org.sopt.play.designsystem.theme.PlaySoptTheme

@Composable
fun Menus(
    modifier: Modifier = Modifier,
    title: List<String>,
    isSelect: String,
    onItemClick: (String) -> Unit
) {
    val lazyListState = rememberLazyListState()

    LaunchedEffect(isSelect) {
        val selectedIndex = title.indexOf(isSelect)
        if (selectedIndex >= 0) {
            lazyListState.animateScrollToItem(selectedIndex)
        }
    }

    LazyRow(
        state = lazyListState,
        modifier = modifier
            .fillMaxWidth()
            .background(PlaySoptTheme.color.white)
    ) {
        items(title.size) { index ->
            val menu = title[index]
            TabMenu(
                title = menu,
                isSelect = menu == isSelect,
                onClick = { onItemClick(menu) }
            )
        }
    }
}

@Preview
@Composable
private fun MenusPreview() {
    PlaySoptPreviewTheme {
        Menus(
            title = listOf("2배 적립", "홈", "셀렉티드", "브랜드", "뷰티", "라이프", "랭킹"),
            isSelect = "홈",
            onItemClick = {}
        )
    }
}