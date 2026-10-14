package org.sopt.play.feature.home.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.core.ui.ext.playSoptDefault
import org.sopt.play.designsystem.component.PlaySoptTopBar
import org.sopt.play.designsystem.theme.PlaySoptPreviewTheme
import org.sopt.play.designsystem.theme.PlaySoptTheme
import org.sopt.play.feature.home.component.Banners
import org.sopt.play.feature.home.component.Categories
import org.sopt.play.feature.home.component.Menus
import org.sopt.play.feature.home.component.ProductCard
import org.sopt.play.feature.home.model.BannerDummies
import org.sopt.play.feature.home.model.ProductDummies

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val gridHeight = ((ProductDummies.productList.size + 1) / 2 * 330).dp

    Column(
        modifier = modifier.playSoptDefault(PlaySoptTheme.color.white)
    ) {
        PlaySoptTopBar(
            onSearchClick = {}
        )
        Column(
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            Menus(
                title = listOf("2배 적립", "홈", "셀렉티드", "브랜드", "뷰티", "라이프", "랭킹"),
                isSelect = "홈",
                onItemClick = {}
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Banners(banners = BannerDummies.bannerList)
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        text = "김솝트님을 위한 추천 아이템",
                        style = PlaySoptTheme.typography.m18
                    )
                    Categories(
                        title = listOf("전체", "상의", "팬츠", "원피스", "아우터", "스커트", "슈즈", "가방", "이너웨어/잠옷", "폰꾸미기"),
                        isSelect = "전체",
                        onItemClick = {}
                    )
                }
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(gridHeight),
                    userScrollEnabled = false,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    items(ProductDummies.productList.size) { index ->
                        ProductCard(
                            product = ProductDummies.productList[index],
                            isLiked = false,
                            onLikeClick = {},
                            onItemClick = {}
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(bottom = 96.dp))
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    PlaySoptPreviewTheme {
        HomeScreen()
    }
}