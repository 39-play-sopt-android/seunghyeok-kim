package org.sopt.play.feature.search.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.core.designsystem.component.PlaySoptTextField
import org.sopt.play.core.designsystem.theme.PlaySoptPreviewTheme
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.core.domain.model.Post
import org.sopt.play.core.ui.ext.playSoptDefault
import org.sopt.play.feature.search.SearchIntent
import org.sopt.play.feature.search.SearchUiState
import org.sopt.play.feature.search.component.FloatingButton
import org.sopt.play.feature.search.component.PostBottomSheet
import org.sopt.play.feature.search.component.PostCard
import org.sopt.play.feature.search.component.PostModal

@Composable
fun SearchScreen(
    uiState: SearchUiState = SearchUiState.Success(),
    onIntent: (SearchIntent) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val searchState = rememberTextFieldState()

    LaunchedEffect(searchState) {
        snapshotFlow { searchState.text.toString() }
            .collect { keyword ->
                onIntent(SearchIntent.SearchKeyword(keyword))
            }
    }

    val successState = uiState as? SearchUiState.Success ?: SearchUiState.Success()

    Box(
        modifier = modifier
            .playSoptDefault(color = PlaySoptTheme.color.white)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(PlaySoptTheme.color.white)
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                PlaySoptTextField(
                    title = "",
                    state = searchState,
                    hint = "검색어를 입력하세요",
                    onSearchable = {}
                )
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    items = successState.posts,
                    key = { it.id }
                ) { post ->
                    PostCard(
                        post = post,
                        onLongClick = {
                            onIntent(SearchIntent.OpenDeleteModal(post.id))
                        }
                    )
                }
            }
        }

        FloatingButton(
            onClick = {
                onIntent(SearchIntent.OpenBottomSheet())
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 20.dp, end = 16.dp)
        )

        if (successState.isBottomSheetOpen) {
            PostBottomSheet(
                onDismiss = { onIntent(SearchIntent.CloseBottomSheet) },
                onSubmit = { title, content ->
                    if (successState.editingPost != null) {
                        onIntent(SearchIntent.UpdatePost(successState.editingPost.id, title, content))
                    } else {
                        onIntent(SearchIntent.CreatePost(title, content))
                    }
                }
            )
        }

        if (successState.deletingPostId != null) {
            PostModal(
                onDismiss = { onIntent(SearchIntent.CloseDeleteModal) },
                onDeleteClick = {
                    onIntent(SearchIntent.DeletePost(successState.deletingPostId))
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchScreenPreview() {
    PlaySoptPreviewTheme {
        SearchScreen(
            uiState = SearchUiState.Success(
                posts = listOf(
                    Post(
                        id = 1L,
                        title = "글쓰기 테스트입니다 하하하하",
                        content = "본문입니다. 본문입니다. 본문입니다. 본문입니다.",
                        author = "솝티",
                        createdAt = "2026.10.24"
                    ),
                    Post(
                        id = 2L,
                        title = "솝솝프렌즈",
                        content = "세미나 열심히 듣고 계시죠? 파이팅!! ㅋㅋ",
                        author = "한나",
                        createdAt = "2026.10.19"
                    ),
                    Post(
                        id = 3L,
                        title = "안드들아",
                        content = "지각하면 가만안드 👊",
                        author = "상엽",
                        createdAt = "2026.10.06"
                    )
                )
            )
        )
    }
}
