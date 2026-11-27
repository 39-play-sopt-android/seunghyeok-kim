package org.sopt.play.feature.search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.core.designsystem.theme.PlaySoptPreviewTheme
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.core.ui.ext.nullIndication
import org.sopt.play.feature.search.R

@Composable
fun FloatingButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .shadow(
                elevation = 12.dp,
                shape = CircleShape,
                ambientColor = Color.Black.copy(alpha = 0.15f),
                spotColor = Color.Black.copy(alpha = 0.15f)
            )
            .size(60.dp)
            .clip(CircleShape)
            .background(color = PlaySoptTheme.color.red)
            .nullIndication(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_plus),
            contentDescription = "추가",
            modifier = Modifier.size(28.dp),
            tint = PlaySoptTheme.color.white
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun FloatingButtonPreview() {
    PlaySoptPreviewTheme {
        FloatingButton(onClick = {})
    }
}
