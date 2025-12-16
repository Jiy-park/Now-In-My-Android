package com.dd2d.now_in_my_android.navigation.modules

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.jiy.screen.career.detail.CareerDetailScreenRoute
import com.jiy.screen.career.detail.routeCareerDetailScreen
import com.jiy.screen.career.list.routeCareerListScreen

internal fun NavGraphBuilder.careerModule(navController: NavHostController) {
  routeCareerListScreen(
    onBack = navController::popBackStack,
    onCareerClick = { careerId ->
      navController.navigate(CareerDetailScreenRoute(careerId))
    },
    modifier = Modifier.fillMaxSize(),
  )
  routeCareerDetailScreen(onBack = navController::popBackStack)
}