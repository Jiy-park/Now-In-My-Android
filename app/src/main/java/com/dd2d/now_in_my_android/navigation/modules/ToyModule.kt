package com.dd2d.now_in_my_android.navigation.modules

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.jiy.screen.toy.detail.ToyDetailScreenRoute
import com.jiy.screen.toy.detail.routeToyDetailScreen
import com.jiy.screen.toy.list.routeToyListScreen

internal fun NavGraphBuilder.toyModule(navController: NavHostController) {
  routeToyListScreen(
    onBack = navController::popBackStack,
    onToyClick = { toyId ->
      navController.navigate(ToyDetailScreenRoute(toyId))
    },
  )
  routeToyDetailScreen(onBack = navController::popBackStack)
}