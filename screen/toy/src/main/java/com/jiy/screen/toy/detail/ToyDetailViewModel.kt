package com.jiy.screen.toy.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.jiy.core.state.Stateful
import com.jiy.core.state.statefulFlowWith
import com.joy.toy.domain.toy_core.ToyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ToyDetailViewModel @Inject constructor(
  savedStateHandle: SavedStateHandle,
  toyRepository: ToyRepository
) : ViewModel() {
  val toyDetailScreenRoute = savedStateHandle.toRoute<ToyDetailScreenRoute>()

  val toyDetailState = toyRepository
    .statefulFlowWith { getToyDetail(toyDetailScreenRoute.id) }
    .stateIn(scope = viewModelScope, started = SharingStarted.WhileSubscribed(5000), initialValue = Stateful.Loading)
}