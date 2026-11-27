package org.sopt.play.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import org.sopt.play.feature.home.model.HomeIntent
import org.sopt.play.feature.home.screen.HomeScreen

@Composable
fun HomeRoute(
    onSearchClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        modifier = modifier,
        uiState = uiState,
        onIntent = { intent ->
            if (intent is HomeIntent.ClickSearch) {
                onSearchClick()
            } else {
                viewModel.onIntent(intent)
            }
        }
    )
}
