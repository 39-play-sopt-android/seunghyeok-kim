package org.sopt.play.feature.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.core.designsystem.R
import org.sopt.play.core.ui.ext.nullIndication
import org.sopt.play.designsystem.theme.PlaySoptPreviewTheme
import org.sopt.play.designsystem.theme.PlaySoptTheme
import org.sopt.play.feature.home.model.Product
import org.sopt.play.feature.home.model.ProductDummies
import org.sopt.play.feature.home.ui.calculateDiscountRate
import org.sopt.play.feature.home.ui.toDecimalFormat

@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    product: Product,
    isLiked: Boolean = false,
    onLikeClick: () -> Unit,
    onItemClick: () -> Unit
) {
    Column(
        modifier = modifier.nullIndication(onClick = onItemClick)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(4f / 5f)
        ) {
            Image(
                painter = painterResource(id = product.thumbnail),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            PlaySoptLikeButton(
                modifier = Modifier.align(Alignment.BottomEnd),
                isLiked = isLiked,
                onClick = onLikeClick
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, start = 8.dp, bottom = 20.dp, end = 8.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = product.storeName,
                style = PlaySoptTheme.typography.m14,
                maxLines = 1,
                overflow = Ellipsis
            )
            Text(
                text = product.productName,
                style = PlaySoptTheme.typography.r14,
                maxLines = 1,
                overflow = Ellipsis
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "${product.price.calculateDiscountRate(product.finalPrice)}%",
                    style = PlaySoptTheme.typography.m14,
                    color = PlaySoptTheme.color.red
                )
                Text(
                    text = product.finalPrice.toDecimalFormat(),
                    style = PlaySoptTheme.typography.m14
                )
            }
        }
    }
}

@Composable
fun PlaySoptLikeButton(
    modifier: Modifier = Modifier,
    isLiked: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .padding(all = 8.dp)
            .nullIndication(onClick = onClick),
    ) {
        Icon(
            painter = painterResource(
                id = if (isLiked) R.drawable.ic_favorite_fill else R.drawable.ic_favorite
            ),
            contentDescription = "좋아요",
            tint = if (isLiked) PlaySoptTheme.color.red else PlaySoptTheme.color.white,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview
@Composable
private fun ProductCardPreview() {
    PlaySoptPreviewTheme {
        ProductCard(
            modifier = Modifier.weight(1f),
            product = ProductDummies.productList[2],
            isLiked = false,
            onLikeClick = {},
            onItemClick = {}
        )
        ProductCard(
            modifier = Modifier.weight(1f),
            product = ProductDummies.productList[1],
            isLiked = true,
            onLikeClick = {},
            onItemClick = {}
        )
    }
}