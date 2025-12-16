package com.jiy.screen.career.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.jiy.career.domain.CareerRepository
import com.jiy.core.state.Stateful
import com.jiy.core.state.statefulFlowWith
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CareerDetailViewModel @Inject constructor(
  savedStateHandle: SavedStateHandle,
  careerRepository: CareerRepository,
) : ViewModel() {
  val careerDetailScreenRoute = savedStateHandle.toRoute(CareerDetailScreenRoute::class)
  val careerDetailState = careerRepository
    .statefulFlowWith { getCareer(careerDetailScreenRoute.careerId) }
    .stateIn(scope = viewModelScope, started = WhileSubscribed(5000), initialValue = Stateful.Loading)
}