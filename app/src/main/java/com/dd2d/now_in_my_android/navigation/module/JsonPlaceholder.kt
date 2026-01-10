package com.dd2d.now_in_my_android.navigation.module

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.dd2d.json_placeholder.presentation.detail.PostDetailScreenRoute
import com.dd2d.json_placeholder.presentation.detail.routePostDetailScreen
import com.dd2d.json_placeholder.presentation.list.routePostListScreen

fun NavGraphBuilder.jsonPlaceholder(navController: NavHostController) {
  routePostListScreen(
    onPostClick = { postId ->
      navController.navigate(PostDetailScreenRoute(postId))
    }
  )
  routePostDetailScreen(onBack = navController::popBackStack)
}
