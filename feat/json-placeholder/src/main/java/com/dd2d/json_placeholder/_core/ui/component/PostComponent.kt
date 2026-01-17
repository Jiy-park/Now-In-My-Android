package com.dd2d.json_placeholder._core.ui.component

import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.dd2d.json_placeholder.post.domain.model.Post

@Composable
internal fun PostComponent(
  post: Post,
  modifier: Modifier = Modifier
) {
  ListItem(
    overlineContent = {
      AuthorComponent(
        authorNickname = post.author.nickname,
        authorProfileImageUrl = post.author.profileImageUrl,
      )
    },
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