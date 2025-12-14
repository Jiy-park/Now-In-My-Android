package com.dd2d.now_in_my_android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.dd2d.now_in_my_android.navigation.modules.mainModule

@Composable
internal fun AppNavHost(
  startDestination: Any,
  modifier: Modifier = Modifier,
  navController: NavHostController = rememberNavController(),
) {
  NavHost(
    navController = navController,
    startDestination = startDestination,
    modifier = modifier
  ) {
    mainModule(navController)
  }
}