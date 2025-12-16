package com.jiy.screen.career.list


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jiy.career.domain.CareerRepository
import com.jiy.career.domain.model.CareerListOption
import com.jiy.core.state.Stateful
import com.jiy.core.state.statefulFlowWith
import com.jiy.user.domain.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CareerListViewModel @Inject constructor(
  userRepository: UserRepository,
  careerRepository: CareerRepository,
) : ViewModel() {
  // 내 정보
  val userState = userRepository
    .statefulFlowWith { me() }
    .stateIn(scope = viewModelScope, started = WhileSubscribed(5000), initialValue = Stateful.Loading)

  // 경력 목록
  val careerListState = careerRepository
    .statefulFlowWith { getMyCareers(option = CareerListOption()) }
    .stateIn(scope = viewModelScope, started = WhileSubscribed(5000), initialValue = Stateful.Loading)
}