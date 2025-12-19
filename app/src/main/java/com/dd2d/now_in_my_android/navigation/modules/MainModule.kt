package com.dd2d.now_in_my_android.navigation.modules

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.dd2d.now_in_my_android.webview.WebviewScreenRoute
import com.jiy.screen.career.detail.CareerDetailScreenRoute
import com.jiy.screen.career.list.CareerListScreenRoute
import com.jiy.screen.main.home.model.MainScreenNavEvent
import com.jiy.screen.main.home.routeMainScreen

internal fun NavGraphBuilder.mainModule(
  navController: NavHostController,
) {
  routeMainScreen(
    onNavEvent = { event ->
      when(event) {
        is MainScreenNavEvent.GitHub -> { navController.navigate(WebviewScreenRoute(title = "GitHub", event.url)) }
        is MainScreenNavEvent.CareerDetail -> { navController.navigate(CareerDetailScreenRoute(event.id)) }
        is MainScreenNavEvent.CareerList -> { navController.navigate(CareerListScreenRoute) }
      }
    },
    modifier = Modifier.fillMaxSize(),
  )
}