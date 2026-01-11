package com.dd2d.json_placeholder.user.presentation.detail

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class UserDetailScreenRoute(val userId: Int)

fun NavGraphBuilder.routeUserDetailScreen(
  onBack: () -> Unit,
  onPostClick: (postId: Int) -> Unit,
  modifier: Modifier = Modifier,
) {
  composable<UserDetailScreenRoute> {
    UserDetailScreen(
      onBack = onBack,
      onPostClick = onPostClick,
      modifier = modifier,
    )
  }
}