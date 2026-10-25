package org.sopt.play.feature.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.core.designsystem.theme.PlaySoptPreviewTheme
import org.sopt.play.feature.home.model.Banner
import org.sopt.play.feature.home.model.BannerDummies

@Composable
fun Banners(
    modifier: Modifier = Modifier,
    banners: List<Banner>
) {
    if (banners.isEmpty()) return

    val pagerState = rememberPagerState(pageCount = { banners.size })

    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 16.dp),
        pageSpacing = 6.dp,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) { page ->
        val banner = banners[page]

        Image(
            painter = painterResource(id = banner.thumbnail),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(328f / 195f)
        )
    }
}

@Preview
@Composable
private fun BannersPreview() {
    PlaySoptPreviewTheme {
        Banners(banners = BannerDummies.bannerList)
    }
}
