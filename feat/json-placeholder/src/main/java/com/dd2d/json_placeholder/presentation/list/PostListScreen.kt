package com.dd2d.json_placeholder.presentation.list

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dd2d.core.stateful.Stateful
import com.dd2d.json_placeholder.presentation.list.content.PostListErrorContent
import com.dd2d.json_placeholder.presentation.list.content.PostListLoadingContent
import com.dd2d.json_placeholder.presentation.list.content.PostListSuccessContent

@Composable
fun PostListScreen(
  modifier: Modifier = Modifier,
  onPostClick: (id: Int) -> Unit,
  viewModel: PostListViewModel = hiltViewModel()
) {
  val postListState by viewModel.postListState.collectAsStateWithLifecycle()

  Scaffold(modifier = modifier) { inner ->
    Crossfade(
      targetState = postListState,
      modifier = Modifier
        .consumeWindowInsets(inner)
        .fillMaxSize()
        .padding(inner)
        .padding(horizontal = 16.dp, vertical = 20.dp)
    ) { state ->
      when(state) {
        is Stateful.Loading -> PostListLoadingContent(modifier = Modifier.fillMaxSize())
        is Stateful.Error -> PostListErrorContent(throwable = state.exception, modifier = Modifier.fillMaxSize())
        is Stateful.Success -> {
          PostListSuccessContent(
            postList = state.data,
            onPostClick = onPostClick,
            modifier = Modifier
              .fillMaxSize()
          )
        }
      }
    }
  }
}