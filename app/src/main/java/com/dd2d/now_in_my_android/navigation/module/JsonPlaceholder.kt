package com.dd2d.now_in_my_android.navigation.module

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.dd2d.json_placeholder.album.presentation.detail.AlbumDetailScreenRoute
import com.dd2d.json_placeholder.album.presentation.detail.routeAlbumDetailScreen
import com.dd2d.json_placeholder.post.presentation.detail.PostDetailScreenRoute
import com.dd2d.json_placeholder.post.presentation.detail.routePostDetailScreen
import com.dd2d.json_placeholder.user.presentation.detail.UserDetailScreenRoute
import com.dd2d.json_placeholder.user.presentation.detail.routeUserDetailScreen

fun NavGraphBuilder.jsonPlaceholder(navController: NavHostController) {
    routePostDetailScreen(
      onBack = navController::popBackStack,
      onUserClick = { userId ->
        navController.navigate(UserDetailScreenRoute(userId))
      },
    )
    routeUserDetailScreen(
      onBack = navController::popBackStack,
      onPostClick = { postId ->
        navController.navigate(PostDetailScreenRoute(postId))
      },
      onAlbumClick = { albumId ->
        navController.navigate(AlbumDetailScreenRoute(albumId))
      }
    )
    routeAlbumDetailScreen(
      onBack = navController::popBackStack,
      onAlbumAuthorClick = { authorId ->
        navController.navigate(UserDetailScreenRoute(userId = authorId))
      },
    )
  }
