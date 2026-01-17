package com.dd2d.json_placeholder._core.ui.content

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dd2d.core.stateful.Stateful

@Composable
internal fun <T> StatefulContent(
  state: Stateful<T>,
  errorMessage: (throwable: Throwable) -> String,
  modifier: Modifier = Modifier,
  successContent: @Composable (data: T) -> Unit,
) {
  Crossfade(
    targetState = state,
    modifier = modifier
  ) { state ->
    when(state) {
      is Stateful.Loading -> LoadingContent(modifier = Modifier.fillMaxSize())
      is Stateful.Error -> {
        ErrorContent(
          message = errorMessage(state.exception),
          throwable = state.exception,
          modifier = Modifier.fillMaxSize()
        )
      }
      is Stateful.Success -> successContent(state.data)
    }
  }
}