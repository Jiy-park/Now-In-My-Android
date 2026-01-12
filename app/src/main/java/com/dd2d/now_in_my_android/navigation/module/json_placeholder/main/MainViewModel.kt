package com.dd2d.now_in_my_android.navigation.module.json_placeholder.main


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.dd2d.now_in_my_android.navigation.module.json_placeholder.main.model.MainScreenBottomNavItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
  savedStateHandle: SavedStateHandle
) : ViewModel() {
  val mainScreenRoute = savedStateHandle.toRoute<MainScreenRoute>()

  private val _selectedItemState = MutableStateFlow(
    value = MainScreenBottomNavItem.entries[mainScreenRoute.initialItemIndex]
  )
  val selectedItemState = _selectedItemState.asStateFlow()
  fun chaneItem(newItem: MainScreenBottomNavItem) {
    _selectedItemState.update { newItem }
  }
}