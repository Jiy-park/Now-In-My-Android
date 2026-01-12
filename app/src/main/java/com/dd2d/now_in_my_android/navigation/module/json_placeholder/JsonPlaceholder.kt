package com.dd2d.now_in_my_android.navigation.module.json_placeholder

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.dd2d.json_placeholder.album.presentation.detail.AlbumDetailScreenRoute
import com.dd2d.json_placeholder.album.presentation.detail.routeAlbumDetailScreen
import com.dd2d.json_placeholder.post.presentation.detail.PostDetailScreenRoute
import com.dd2d.json_placeholder.post.presentation.detail.routePostDetailScreen
import com.dd2d.json_placeholder.user.presentation.detail.UserDetailScreenRoute
import com.dd2d.json_placeholder.user.presentation.detail.routeUserDetailScreen
import com.dd2d.now_in_my_android.navigation.module.json_placeholder.main.MainScreenRoute
import com.dd2d.now_in_my_android.navigation.module.json_placeholder.main.model.MainScreenNavEvent
import com.dd2d.now_in_my_android.navigation.module.json_placeholder.main.routeMainScreen
import kotlinx.serialization.Serializable

@Serializable
data object JsonPlaceholder

fun NavGraphBuilder.jsonPlaceholder(navController: NavHostController) {
  navigation<JsonPlaceholder>(startDestination = MainScreenRoute()) {
    routeMainScreen(
      onNavEvent = { event ->
        when(event) {
          is MainScreenNavEvent.PostDetail -> navController.navigate(PostDetailScreenRoute(event.postId))
        }
      }
    )
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
}