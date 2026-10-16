package org.sopt.play.feature.home.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.sopt.play.core.ui.ext.playSoptDefault
import org.sopt.play.designsystem.component.PlaySoptTopBar
import org.sopt.play.designsystem.theme.PlaySoptTheme
import org.sopt.play.feature.home.component.Banners
import org.sopt.play.feature.home.component.Categories
import org.sopt.play.feature.home.component.Menus
import org.sopt.play.feature.home.component.ProductCard
import org.sopt.play.feature.home.model.BannerDummies
import org.sopt.play.feature.home.model.HomeIntent
import org.sopt.play.feature.home.model.HomeUiState
import org.sopt.play.feature.home.model.ProductDummies

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeUiState = HomeUiState.Default(),
    onIntent: (HomeIntent) -> Unit = {}
) {
    val defaultState = uiState as? HomeUiState.Default ?: HomeUiState.Default()
    val selectedMenu = defaultState.selectedMenu
    val selectedCategory = defaultState.selectedCategory
    val likedProductNames = defaultState.likedProductNames

    val menuList = listOf("2배 적립", "홈", "셀렉티드", "브랜드", "뷰티", "라이프", "랭킹")
    val initialPageIndex = menuList.indexOf(selectedMenu).coerceAtLeast(0)

    val pagerState = rememberPagerState(
        initialPage = initialPageIndex,
        pageCount = { menuList.size }
    )
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(pagerState.currentPage) {
        val newMenu = menuList[pagerState.currentPage]
        if (newMenu != selectedMenu) {
            onIntent(HomeIntent.SelectMenu(newMenu))
        }
    }

    LaunchedEffect(selectedMenu) {
        val targetIndex = menuList.indexOf(selectedMenu)
        if (targetIndex >= 0 && targetIndex != pagerState.currentPage) {
            pagerState.animateScrollToPage(targetIndex)
        }
    }

    val itemCount = ProductDummies.productList.size
    val rowCount = (itemCount + 1) / 2
    val gridHeight = if (rowCount > 0) (rowCount * 315 + (rowCount - 1) * 12).dp else 0.dp //수정 필요 (현재는 height(315) 하드 코딩 방식)

    Column(
        modifier = modifier.playSoptDefault(color = PlaySoptTheme.color.white, topPadding = false)
    ) {
        PlaySoptTopBar(
            onSearchClick = { onIntent(HomeIntent.ClickSearch) }
        )
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { _ ->
            val pageScrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(pageScrollState)
            ) {
                Menus(
                    title = menuList,
                    isSelect = selectedMenu,
                    onItemClick = { menu ->
                        onIntent(HomeIntent.SelectMenu(menu))
                        val targetIndex = menuList.indexOf(menu)
                        if (targetIndex >= 0) {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(targetIndex)
                            }
                        }
                    }
                )
                Spacer(modifier = Modifier.height(12.dp))
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
                            isSelect = selectedCategory,
                            onItemClick = { category -> onIntent(HomeIntent.SelectCategory(category)) }
                        )
                    }
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(gridHeight),
                        userScrollEnabled = false,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(itemCount) { index ->
                            val product = ProductDummies.productList[index]
                            val isLiked = likedProductNames.contains(product.productName)

                            ProductCard(
                                product = product,
                                isLiked = isLiked,
                                onLikeClick = { onIntent(HomeIntent.ToggleLike(product.productName)) },
                                onItemClick = { onIntent(HomeIntent.ClickProduct(product.productName)) }
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(96.dp))
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    PlaySoptTheme {
        HomeScreen()
    }
}