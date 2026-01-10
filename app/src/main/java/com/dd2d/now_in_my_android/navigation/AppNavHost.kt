package com.dd2d.now_in_my_android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.dd2d.json_placeholder.presentation.list.PostListScreenRoute
import com.dd2d.now_in_my_android.navigation.module.jsonPlaceholder

@Composable
fun AppNavHost(
  modifier: Modifier = Modifier,
  navController: NavHostController = rememberNavController(),
) {
  NavHost(
    navController = navController,
    startDestination = PostListScreenRoute,
    modifier = modifier
  ) {
    jsonPlaceholder(navController)
  }
}