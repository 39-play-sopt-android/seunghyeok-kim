package org.sopt.play.feature.search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.core.designsystem.theme.PlaySoptPreviewTheme
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.core.domain.model.Post
import org.sopt.play.core.ui.ext.singleCombinedClickable

@Composable
fun PostCard(
    post: Post,
    onClick: () -> Unit = {},
    onLongClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(PlaySoptTheme.color.gray1)
            .singleCombinedClickable(
                onLongClick = onLongClick,
                onClick = onClick
            )
            .padding(all = 20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = post.author,
                style = PlaySoptTheme.typography.m12,
                color = PlaySoptTheme.color.gray3
            )
            Box(
                modifier = Modifier
                    .size(width = 1.dp, height = 12.dp)
                    .background(PlaySoptTheme.color.gray3)
            )
            Text(
                text = post.createdAt,
                style = PlaySoptTheme.typography.m12,
                color = PlaySoptTheme.color.gray3
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = post.title,
                style = PlaySoptTheme.typography.m16,
                color = PlaySoptTheme.color.gray5
            )
            Text(
                text = post.content,
                style = PlaySoptTheme.typography.m14,
                color = PlaySoptTheme.color.gray5
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PostCardPreview() {
    PlaySoptPreviewTheme {
        PostCard(
            post = Post(
                id = 1L,
                title = "제목",
                content = "본문입니다.",
                author = "작성자",
                createdAt = "2026.09.24"
            )
        )
    }
}
