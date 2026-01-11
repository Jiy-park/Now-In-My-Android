package com.dd2d.json_placeholder.post.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dd2d.json_placeholder._core.ui.component.PostComponent
import com.dd2d.json_placeholder._core.ui.content.StatefulContent
import com.dd2d.json_placeholder.post.domain.model.Post

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

@Composable
private fun PostListContent(
  postList: List<Post>,
  onPostClick: (id: Int) -> Unit,
  modifier: Modifier = Modifier,
  contentPadding: PaddingValues = PaddingValues()
) {
  Column(modifier = modifier) {
    LazyColumn(
      verticalArrangement = Arrangement.spacedBy(16.dp),
      contentPadding = contentPadding,
      modifier = Modifier.fillMaxSize()
    ) {
      items(items = postList, key = Post::id) { post ->
        PostComponent(
          post = post,
          modifier = Modifier
            .animateItem()
            .fillMaxWidth()
            .clickable { onPostClick(post.id) }
        )
      }
    }
  }
}