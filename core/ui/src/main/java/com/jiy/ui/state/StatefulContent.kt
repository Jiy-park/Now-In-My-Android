package com.jiy.ui.state

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jiy.core.exception.UIException
import com.jiy.core.state.Stateful

@Composable
fun <T> StatefulContent(
  state: Stateful<T>,
  loadingContent: @Composable () -> Unit = {  },
  errorContent: @Composable (exception: UIException) -> Unit = {},
  successContent: @Composable (data: T) -> Unit,
  modifier: Modifier = Modifier
) {
  Crossfade(
    targetState = state,
    modifier = modifier
  ) { state ->
    when(state) {
      is Stateful.Loading -> loadingContent()
      is Stateful.Error -> errorContent(state.exception)
      is Stateful.Success -> successContent(state.data)
    }
  }
}