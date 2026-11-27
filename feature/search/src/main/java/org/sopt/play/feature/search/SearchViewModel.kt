package org.sopt.play.feature.search

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed interface SearchUiState {
    data object Default : SearchUiState
}

class SearchViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<SearchUiState>(SearchUiState.Default)
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()
}
