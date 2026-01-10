package com.dd2d.json_placeholder.post.presentation.list

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object PostListScreenRoute

fun NavGraphBuilder.routePostListScreen(
  onPostClick: (postId: Int) -> Unit,
  modifier: Modifier = Modifier,
) {
  composable<PostListScreenRoute> {
    PostListScreen(
      onPostClick = onPostClick,
      modifier = modifier,
    )
  }
}