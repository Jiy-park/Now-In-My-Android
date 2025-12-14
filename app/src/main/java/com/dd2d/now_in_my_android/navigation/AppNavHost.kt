package com.dd2d.now_in_my_android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.dd2d.now_in_my_android.navigation.modules.mainModule
import com.jiy.ui.navigation.ScreenRoute

@Composable
internal fun AppNavHost(
  startDestination: ScreenRoute,
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