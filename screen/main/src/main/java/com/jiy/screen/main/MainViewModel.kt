package com.jiy.screen.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.jiy.screen.main.model.MainScreenTab
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
  savedStateHandle: SavedStateHandle
) : ViewModel() {
  val mainScreenRoute = savedStateHandle.toRoute<MainScreenRoute>()

  private val _currentTab = MutableStateFlow(mainScreenRoute.initialTab)
  val currentTab = _currentTab.asStateFlow()

  fun changeTab(tab: MainScreenTab) {
    _currentTab.value = tab
  }
}