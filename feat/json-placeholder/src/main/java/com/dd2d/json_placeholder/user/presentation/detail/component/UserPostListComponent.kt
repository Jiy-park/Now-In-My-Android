package com.dd2d.json_placeholder.user.presentation.detail.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dd2d.json_placeholder._core.ui.component.PostComponent
import com.dd2d.json_placeholder.post.domain.model.Post

@Composable
internal fun UserPostListComponent(
  postList: List<Post>,
  onPostClick: (id: Int) -> Unit,
  modifier: Modifier = Modifier
) {
  LazyColumn(modifier = modifier) {
    items(items = postList, key = Post::id) { item ->
      PostComponent(
        post = item,
        modifier = Modifier
          .animateItem()
          .fillMaxWidth()
          .clickable { onPostClick(item.id) }
      )
    }
  }
}