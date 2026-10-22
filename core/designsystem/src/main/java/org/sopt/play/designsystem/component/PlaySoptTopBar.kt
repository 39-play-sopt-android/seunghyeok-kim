package org.sopt.play.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.core.designsystem.R
import org.sopt.play.designsystem.theme.PlaySoptPreviewTheme
import org.sopt.play.designsystem.theme.PlaySoptTheme

@Composable
fun PlaySoptTopBar(
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(PlaySoptTheme.color.white)
            .padding(start = 16.dp, end = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "PLAY SOPT",
            style = PlaySoptTheme.typography.b28,
        )
        PlaySoptIconButton(
            iconId = R.drawable.ic_search,
            onClick = onSearchClick,
        )
    }
}

@Preview
@Composable
private fun PlaySoptTopBarPreview() {
    PlaySoptPreviewTheme {
        PlaySoptTopBar(
            onSearchClick = {},
        )
    }
}