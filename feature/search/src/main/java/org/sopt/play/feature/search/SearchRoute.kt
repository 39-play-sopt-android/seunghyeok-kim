package org.sopt.play.feature.search

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel
import org.sopt.play.feature.search.screen.SearchScreen

@Composable
fun SearchRoute(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = koinViewModel()
) {
    BackHandler {
        onBackClick()
    }

    SearchScreen(
        modifier = modifier
    )
}
