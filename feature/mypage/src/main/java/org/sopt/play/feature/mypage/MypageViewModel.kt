package org.sopt.play.feature.mypage

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import org.sopt.play.feature.mypage.model.MypageIntent
import org.sopt.play.feature.mypage.model.MypageUiState

sealed class MypageEvent{

}

class MypageViewModel: ViewModel(){
    private val _uiState = MutableStateFlow<MypageUiState>(MypageUiState.Loading)
    val uiState: StateFlow<MypageUiState> = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<MypageEvent>()
    val event = _event.asSharedFlow()

    private val backStack = mutableListOf<MypageUiState>()

    private fun navTo(next: MypageUiState) {
        if (_uiState.value == next) return
        backStack.add(_uiState.value)
        _uiState.value = next
    }

    fun onIntent(intent: MypageIntent){

    }
}