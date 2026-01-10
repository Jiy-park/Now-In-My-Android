package com.dd2d.json_placeholder.user.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.dd2d.core.stateful.Stateful
import com.dd2d.core.stateful.statefulFlow
import com.dd2d.json_placeholder.user.domain.UserRepository
import com.dd2d.json_placeholder.user.presentation.detail.model.TodoListUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
  savedStateHandle: SavedStateHandle,
  userRepository: UserRepository,
) : ViewModel() {
  val userDetailScreenRoute = savedStateHandle.toRoute<UserDetailScreenRoute>()

  val userDetailState = statefulFlow { userRepository.getUser(userDetailScreenRoute.userId) }
    .stateIn(scope = viewModelScope, started = SharingStarted.WhileSubscribed(5000), initialValue = Stateful.Loading)


  private val todoListCompleteFilter = MutableStateFlow<Boolean?>(null)
  internal val todoListUIState = todoListCompleteFilter
    .flatMapLatest { completeFilter ->
      statefulFlow { userRepository.getTodoList(userDetailScreenRoute.userId, completeFilter) }
    }
    .map { state ->
      when(state) {
        is Stateful.Loading -> Stateful.Loading
        is Stateful.Error -> Stateful.Error(state.exception)
        is Stateful.Success -> {
          Stateful.Success(
            data = TodoListUIState(
              todoList = state.data,
              completeFilter = todoListCompleteFilter.value,
              onFilterChange = { todoListCompleteFilter.value = it },
            )
          )
        }
      }
    }
    .scan(initial = Stateful.Loading) { old: Stateful<TodoListUIState>, new ->
      (new as? Stateful.Success)?: old
    }
    .stateIn(scope = viewModelScope, started = SharingStarted.WhileSubscribed(5000), initialValue = Stateful.Loading)
}