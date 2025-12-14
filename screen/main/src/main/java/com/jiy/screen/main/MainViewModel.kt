package com.jiy.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jiy.core.state.Stateful
import com.jiy.core.state.statefulFlowWith
import com.jiy.user.domain.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
  userRepository: UserRepository
) : ViewModel() {
  val userState = userRepository
    .statefulFlowWith { me() }
    .stateIn(scope = viewModelScope, started = SharingStarted.Eagerly, initialValue = Stateful.Loading)

  val skillStackState = userRepository
    .statefulFlowWith { getMySkillStack() }
    .stateIn(scope = viewModelScope, started = SharingStarted.Eagerly, initialValue = Stateful.Loading)

  val careersState = userRepository
    .statefulFlowWith { getMyCareers() }
    .stateIn(scope = viewModelScope, started = SharingStarted.Eagerly, initialValue = Stateful.Loading)
}