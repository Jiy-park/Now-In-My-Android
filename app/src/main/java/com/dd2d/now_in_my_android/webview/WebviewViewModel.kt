package com.dd2d.now_in_my_android.webview

import androidx.core.os.bundleOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WebviewViewModel @Inject constructor(
  savedStateHandle: SavedStateHandle,
) : ViewModel() {
  val webViewScreenRoute = savedStateHandle.toRoute<WebviewScreenRoute>()
  val webViewState = bundleOf()
}