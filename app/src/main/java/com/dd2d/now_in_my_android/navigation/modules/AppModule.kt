package com.dd2d.now_in_my_android.navigation.modules

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.dd2d.now_in_my_android.webview.routeWebviewScreen

internal fun NavGraphBuilder.appModule(navController: NavHostController) {
  routeWebviewScreen(onBack = navController::popBackStack)
}