package org.sopt.play.feature.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import org.sopt.play.feature.home.model.HomeIntent
import org.sopt.play.feature.home.model.HomeUiState

sealed class HomeEvent

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(
        HomeUiState.Default(
            selectedMenu = "홈",
            selectedCategory = "전체",
            likedProductNames = emptySet(),
        )
    )
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<HomeEvent>()
    val event = _event.asSharedFlow()

    fun onIntent(intent: HomeIntent) {
        val current = _uiState.value as? HomeUiState.Default ?: return
        when (intent) {
            is HomeIntent.ClickSearch -> {

            }
            is HomeIntent.SelectMenu -> {
                _uiState.value = current.copy(selectedMenu = intent.menu)
            }
            is HomeIntent.SelectCategory -> {
                _uiState.value = current.copy(selectedCategory = intent.category)
            }
            is HomeIntent.ToggleLike -> {
                val updatedLikes = current.likedProductNames.toMutableSet().apply {
                    if (contains(intent.productName)) remove(intent.productName)
                    else add(intent.productName)
                }
                _uiState.value = current.copy(likedProductNames = updatedLikes)
            }
            is HomeIntent.ClickProduct -> {

            }
        }
    }
}