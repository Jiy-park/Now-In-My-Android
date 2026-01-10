package com.dd2d.now_in_my_android.navigation.module

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.dd2d.json_placeholder.presentation.list.routePostListScreen

fun NavGraphBuilder.jsonPlaceholder(navController: NavHostController) {
  routePostListScreen(onPostClick = {})
}
