package org.sopt.play.feature.like

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import org.sopt.play.feature.like.model.LikeIntent
import org.sopt.play.feature.like.model.LikeUiState

sealed class LikeEvent{

}

class LikeViewModel: ViewModel(){
    private val _uiState = MutableStateFlow<LikeUiState>(LikeUiState.Loading)
    val uiState: StateFlow<LikeUiState> = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<LikeEvent>()
    val event = _event.asSharedFlow()

    private val backStack = mutableListOf<LikeUiState>()

    private fun navTo(next: LikeUiState) {
        if (_uiState.value == next) return
        backStack.add(_uiState.value)
        _uiState.value = next
    }

    fun onIntent(intent: LikeIntent){

    }
}