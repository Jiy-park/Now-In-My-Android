package com.dd2d.json_placeholder.user.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.dd2d.core.stateful.Stateful
import com.dd2d.core.stateful.statefulFlow
import com.dd2d.json_placeholder.user.domain.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
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
}