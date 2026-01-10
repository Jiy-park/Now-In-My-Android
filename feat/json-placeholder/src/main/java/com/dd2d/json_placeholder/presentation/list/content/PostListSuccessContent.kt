package com.dd2d.json_placeholder.presentation.list.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.dd2d.json_placeholder.domain.post.model.Post

@Composable
internal fun PostListSuccessContent(
  postList: List<Post>,
  onPostClick: (id: Int) -> Unit,
  modifier: Modifier = Modifier
) {
  Column(modifier = modifier) {
    LazyColumn(
      verticalArrangement = Arrangement.spacedBy(16.dp),
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

@Composable
private fun PostComponent(
  post: Post,
  modifier: Modifier = Modifier
) {
  ListItem(
    headlineContent = {
      Text(
        text = post.title,
        fontWeight = FontWeight.W600,
        color = MaterialTheme.colorScheme.onSurface,
        fontSize = 18.sp,
        lineHeight = 1.4.em,
        maxLines = 1
      )
    },
    supportingContent = {
      Text(
        text = post.body,
        fontWeight = FontWeight.W400,
        color = MaterialTheme.colorScheme.onSurface,
        fontSize = 14.sp,
        lineHeight = 1.4.em,
        maxLines = 2
      )
    },
    modifier = modifier,
  )
}

@Preview
@Composable
private fun PostListSuccessContentPrev() {
  PostListSuccessContent(
    postList = List(5) { Post(it, 1, "title $it", "body $it") },
    onPostClick = {},
    modifier = Modifier
      .fillMaxSize()
  )
}