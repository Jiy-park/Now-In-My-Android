package com.dd2d.now_in_my_android.navigation.modules

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.jiy.screen.toy.detail.routeToyDetailScreen

internal fun NavGraphBuilder.toyModule(navController: NavHostController) {
  routeToyDetailScreen(onBack = navController::popBackStack)
}