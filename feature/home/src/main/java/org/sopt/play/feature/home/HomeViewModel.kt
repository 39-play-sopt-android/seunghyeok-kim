package org.sopt.play.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.sopt.play.core.domain.repository.LikeRepository
import org.sopt.play.feature.home.model.HomeIntent
import org.sopt.play.feature.home.model.HomeUiState

sealed class HomeEvent

class HomeViewModel(
    private val likeRepository: LikeRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(
        HomeUiState.Default(
            selectedMenu = "홈",
            selectedCategory = "전체",
            likedProduct = emptySet(),
        )
    )
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<HomeEvent>()
    val event = _event.asSharedFlow()

    init {
        observeLikedProducts()
    }

    private fun observeLikedProducts() {
        likeRepository.likedProductsFlow
            .onEach { likedSet ->
                val current = _uiState.value as? HomeUiState.Default ?: return@onEach
                _uiState.value = current.copy(likedProduct = likedSet)
            }
            .launchIn(viewModelScope)
    }

    fun onIntent(intent: HomeIntent) {
        val current = _uiState.value as? HomeUiState.Default ?: return
        when (intent) {
            is HomeIntent.ClickSearch -> {}
            is HomeIntent.SelectMenu -> {
                _uiState.value = current.copy(selectedMenu = intent.menu)
            }
            is HomeIntent.SelectCategory -> {
                _uiState.value = current.copy(selectedCategory = intent.category)
            }
            is HomeIntent.ToggleLike -> {
                viewModelScope.launch {
                    likeRepository.toggleLike(intent.productId)
                }
            }
            is HomeIntent.ClickProduct -> {}
        }
    }
}
