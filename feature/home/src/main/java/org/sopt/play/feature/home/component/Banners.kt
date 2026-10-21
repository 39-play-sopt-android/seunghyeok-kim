package org.sopt.play.feature.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.designsystem.theme.PlaySoptPreviewTheme
import org.sopt.play.feature.home.model.Banner
import org.sopt.play.feature.home.model.BannerDummies

@Composable
fun Banners(
    modifier: Modifier = Modifier,
    banners: List<Banner>
) {
    if (banners.isEmpty()) return

    val pageCount = Int.MAX_VALUE
    val startIndex = (pageCount / 2) - ((pageCount / 2) % banners.size)
    val lazyListState = rememberLazyListState(initialFirstVisibleItemIndex = startIndex)
    val snapFlingBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)

    //width 사이즈를 기종 기준으로 제작
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp

    LaunchedEffect(banners) {
        if (banners.isNotEmpty()) {
            lazyListState.scrollToItem(startIndex)
        }
    }

    LazyRow(
        state = lazyListState,
        flingBehavior = snapFlingBehavior,
        modifier = modifier.fillMaxWidth().padding(vertical = 8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(
            count = pageCount,
            key = { index ->
                val actualIndex = index % banners.size
                "$index-$actualIndex-${banners[actualIndex].thumbnail}"
            }
        ) { index ->
            val actualIndex = index % banners.size
            val banner = banners[actualIndex]

            Image(
                painter = painterResource(id = banner.thumbnail),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width((screenWidth - 32).dp)
                    .aspectRatio(328f / 195f)
            )
        }
    }
}

@Preview
@Composable
private fun BannersPreview() {
    PlaySoptPreviewTheme {
        Banners(banners = BannerDummies.bannerList)
    }
}