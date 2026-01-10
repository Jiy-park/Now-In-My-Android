package com.dd2d.json_placeholder.presentation.detail

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class PostDetailScreenRoute(val postId: Int)

fun NavGraphBuilder.routePostDetailScreen(
  onBack: () -> Unit,
  modifier: Modifier = Modifier,
) {
  composable<PostDetailScreenRoute> {
    PostDetailScreen(
      onBack = onBack,
      modifier = modifier,
    )
  }
}