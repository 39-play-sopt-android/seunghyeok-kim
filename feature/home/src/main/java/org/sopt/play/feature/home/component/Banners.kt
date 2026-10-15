package org.sopt.play.feature.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
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
    val lazyListState = rememberLazyListState()
    val snapFlingBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)

    LazyRow(
        state = lazyListState,
        flingBehavior = snapFlingBehavior,
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(
            items = banners,
            key = { index, banner -> "${index}_${banner.thumbnail}" }
        ) { _, banner ->
            Image(
                painter = painterResource(id = banner.thumbnail),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(328.dp)
                    .height(195.dp)
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