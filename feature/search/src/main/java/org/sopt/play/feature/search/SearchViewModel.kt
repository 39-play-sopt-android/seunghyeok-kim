package org.sopt.play.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.play.core.domain.model.Post
import org.sopt.play.core.domain.usecase.CreatePostUseCase
import org.sopt.play.core.domain.usecase.DeletePostUseCase
import org.sopt.play.core.domain.usecase.GetPostsUseCase
import org.sopt.play.core.domain.usecase.UpdatePostUseCase

sealed interface SearchUiState {
    data object Loading : SearchUiState
    data class Success(
        val posts: List<Post> = emptyList(),
        val keyword: String = "",
        val isBottomSheetOpen: Boolean = false,
        val editingPost: Post? = null,
        val deletingPostId: Long? = null
    ) : SearchUiState
    data class Error(val message: String) : SearchUiState
}

sealed interface SearchIntent {
    data class SearchKeyword(val keyword: String) : SearchIntent
    data class CreatePost(val title: String, val content: String) : SearchIntent
    data class UpdatePost(val postId: Long, val title: String, val content: String) : SearchIntent
    data class DeletePost(val postId: Long) : SearchIntent
    data class OpenBottomSheet(val editingPost: Post? = null) : SearchIntent
    data object CloseBottomSheet : SearchIntent
    data class OpenDeleteModal(val postId: Long) : SearchIntent
    data object CloseDeleteModal : SearchIntent
}

sealed interface SearchEvent {
    data class ShowToast(val message: String) : SearchEvent
}

class SearchViewModel(
    private val getPostsUseCase: GetPostsUseCase,
    private val createPostUseCase: CreatePostUseCase,
    private val updatePostUseCase: UpdatePostUseCase,
    private val deletePostUseCase: DeletePostUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<SearchUiState>(
        SearchUiState.Success(posts = emptyList())
    )
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<SearchEvent>()
    val event = _event.asSharedFlow()

    init {
        fetchPosts()
    }

    fun onIntent(intent: SearchIntent) {
        when (intent) {
            is SearchIntent.SearchKeyword -> fetchPosts(keyword = intent.keyword)
            is SearchIntent.CreatePost -> createPost(intent.title, intent.content)
            is SearchIntent.UpdatePost -> updatePost(intent.postId, intent.title, intent.content)
            is SearchIntent.DeletePost -> deletePost(intent.postId)
            is SearchIntent.OpenBottomSheet -> openBottomSheet(intent.editingPost)
            is SearchIntent.CloseBottomSheet -> closeBottomSheet()
            is SearchIntent.OpenDeleteModal -> openDeleteModal(intent.postId)
            is SearchIntent.CloseDeleteModal -> closeDeleteModal()
        }
    }

    private fun fetchPosts(keyword: String? = null) {
        val effectiveKeyword = if (keyword.isNullOrBlank()) null else keyword
        viewModelScope.launch {
            getPostsUseCase(keyword = effectiveKeyword)
                .onSuccess { postList ->
                    val current = _uiState.value as? SearchUiState.Success ?: SearchUiState.Success()
                    _uiState.value = current.copy(posts = postList, keyword = keyword ?: "")
                }
                .onFailure { error ->
                    _event.emit(SearchEvent.ShowToast(error.message ?: "게시글을 불러오는데 실패했습니다."))
                }
        }
    }

    private fun createPost(title: String, content: String) {
        viewModelScope.launch {
            createPostUseCase(title, content)
                .onSuccess {
                    _event.emit(SearchEvent.ShowToast("게시글이 작성되었습니다."))
                    closeBottomSheet()
                    fetchPosts()
                }
                .onFailure { error ->
                    _event.emit(SearchEvent.ShowToast(error.message ?: "작성에 실패했습니다."))
                }
        }
    }

    private fun updatePost(postId: Long, title: String, content: String) {
        viewModelScope.launch {
            updatePostUseCase(postId, title, content)
                .onSuccess {
                    _event.emit(SearchEvent.ShowToast("게시글이 수정되었습니다."))
                    closeBottomSheet()
                    fetchPosts()
                }
                .onFailure { error ->
                    _event.emit(SearchEvent.ShowToast(error.message ?: "수정에 실패했습니다."))
                }
        }
    }

    private fun deletePost(postId: Long) {
        viewModelScope.launch {
            deletePostUseCase(postId)
                .onSuccess {
                    _event.emit(SearchEvent.ShowToast("게시글이 삭제되었습니다."))
                    closeDeleteModal()
                    fetchPosts()
                }
                .onFailure { error ->
                    _event.emit(SearchEvent.ShowToast(error.message ?: "삭제에 실패했습니다."))
                }
        }
    }

    private fun openBottomSheet(editingPost: Post? = null) {
        val current = _uiState.value as? SearchUiState.Success ?: return
        _uiState.value = current.copy(isBottomSheetOpen = true, editingPost = editingPost)
    }

    private fun closeBottomSheet() {
        val current = _uiState.value as? SearchUiState.Success ?: return
        _uiState.value = current.copy(isBottomSheetOpen = false, editingPost = null)
    }

    private fun openDeleteModal(postId: Long) {
        val current = _uiState.value as? SearchUiState.Success ?: return
        _uiState.value = current.copy(deletingPostId = postId)
    }

    private fun closeDeleteModal() {
        val current = _uiState.value as? SearchUiState.Success ?: return
        _uiState.value = current.copy(deletingPostId = null)
    }
}
