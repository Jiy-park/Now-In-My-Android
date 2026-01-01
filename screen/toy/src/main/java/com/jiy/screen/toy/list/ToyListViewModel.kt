package com.jiy.screen.toy.list

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
class ToyListViewModel @Inject constructor(
  toyRepository: ToyRepository,
) : ViewModel() {
  val toyList = toyRepository
    .statefulFlowWith { getToyList(option = ToyListOption()) }
    .stateIn(scope = viewModelScope, started = SharingStarted.WhileSubscribed(), initialValue = Stateful.Loading)
}