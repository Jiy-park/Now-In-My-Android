package com.dd2d.json_placeholder.post.presentation.list

import androidx.compose.foundation.layout.PaddingValues
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
import com.dd2d.json_placeholder.post.presentation._core.content.StatefulContent
import com.dd2d.json_placeholder.post.presentation.list.content.PostListContent

@Composable
fun PostListScreen(
  modifier: Modifier = Modifier,
  onPostClick: (id: Int) -> Unit,
  viewModel: PostListViewModel = hiltViewModel()
) {
  val postListState by viewModel.postListState.collectAsStateWithLifecycle()

  Scaffold(modifier = modifier) { inner ->
    StatefulContent(
      state = postListState,
      errorMessage = { "Post 목록을 불러오지 못했습니다." },
      modifier = Modifier
        .consumeWindowInsets(inner)
        .fillMaxSize()
        .padding(inner)
    ) { postList ->
      PostListContent(
        postList = postList,
        onPostClick = onPostClick,
        contentPadding = PaddingValues(vertical = 20.dp),
        modifier = Modifier
          .fillMaxSize()
      )
    }
  }
}