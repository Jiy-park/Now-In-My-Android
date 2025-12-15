package com.dd2d.now_in_my_android.navigation.modules

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.jiy.screen.career.list.routeCareerListScreen

internal fun NavGraphBuilder.careerModule(navController: NavController) {
  routeCareerListScreen(
    onBack = navController::popBackStack,
    onCareerClick = {  },
    modifier = Modifier.fillMaxSize(),
  )
}