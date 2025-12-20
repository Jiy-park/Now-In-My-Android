package com.jiy.screen.main.playground

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jiy.core.state.Stateful
import com.jiy.core.state.statefulFlowWith
import com.joy.toy.domain.toy_core.ToyRepository
import com.joy.toy.domain.toy_core.model.ToyListOption
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class PlaygroundViewModel @Inject constructor(
  toyRepository: ToyRepository,
) : ViewModel() {
  // 장난감 목록 상태. 5개만 보여주고 그 이상은 목록 화면으로 이동후 제공
  val toyListState = toyRepository
    .statefulFlowWith { getToyList(option = ToyListOption(size = 5)) }
    .stateIn(scope = viewModelScope, started = SharingStarted.Eagerly, initialValue = Stateful.Loading)
}