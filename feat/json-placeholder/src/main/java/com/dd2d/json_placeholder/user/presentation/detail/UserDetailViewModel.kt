package com.dd2d.json_placeholder.user.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.dd2d.core.flow.stateInWhileSubscribed
import com.dd2d.core.stateful.Stateful
import com.dd2d.core.stateful.mapSuccessState
import com.dd2d.core.stateful.statefulFlow
import com.dd2d.json_placeholder.album.domain.AlbumRepository
import com.dd2d.json_placeholder.post.domain.PostRepository
import com.dd2d.json_placeholder.user.domain.UserRepository
import com.dd2d.json_placeholder.user.presentation.detail.model.TodoListUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
  savedStateHandle: SavedStateHandle,
  userRepository: UserRepository,
  postRepository: PostRepository,
  albumRepository: AlbumRepository,
) : ViewModel() {
  val userDetailScreenRoute = savedStateHandle.toRoute<UserDetailScreenRoute>()

  /** 유저 상세 상태 */
  val userDetailState = statefulFlow { userRepository.getUser(userDetailScreenRoute.userId) }
    .stateInWhileSubscribed(scope = viewModelScope, initialValue = Stateful.Loading)

  /** 유저 할일 목록 */
  private val todoListCompleteFilter = MutableStateFlow<Boolean?>(null)
  fun changeTodoListCompleteFilter(value: Boolean?) {
    todoListCompleteFilter.update { value  }
  }
  internal val todoListUIState = todoListCompleteFilter
    .flatMapLatest { completeFilter ->
      statefulFlow { userRepository.getTodoList(userDetailScreenRoute.userId, completeFilter) }
    }
    .mapSuccessState { todoList ->
      TodoListUIState(
        todoList = todoList,
        completeFilter = todoListCompleteFilter.value,
      )
    }
    .scan(initial = Stateful.Loading) { old: Stateful<TodoListUIState>, new ->
      (new as? Stateful.Success)?: old
    }
    .stateInWhileSubscribed(scope = viewModelScope, initialValue = Stateful.Loading)

  /** 유저 작성 게시물 */
  internal val postListState = statefulFlow { postRepository.getPostList(authorId = userDetailScreenRoute.userId) }
    .stateInWhileSubscribed(scope = viewModelScope, initialValue = Stateful.Loading)

  /** 유저 작성 앨범 */
  internal val albumListState = statefulFlow { albumRepository.getAlbumList(authorId = userDetailScreenRoute.userId) }
    .stateInWhileSubscribed(scope = viewModelScope, initialValue = Stateful.Loading)
}