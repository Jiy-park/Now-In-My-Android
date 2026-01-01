package com.dd2d.now_in_my_android.navigation.modules

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.dd2d.now_in_my_android.webview.WebviewScreenRoute
import com.jiy.screen.career.detail.CareerDetailScreenRoute
import com.jiy.screen.career.list.CareerListScreenRoute
import com.jiy.screen.main.model.MainScreenNavEvent
import com.jiy.screen.main.routeMainScreen
import com.jiy.screen.toy.detail.ToyDetailScreenRoute
import com.jiy.screen.toy.list.ToyListScreenRoute

internal fun NavGraphBuilder.mainModule(navController: NavHostController) {
  routeMainScreen(
    onNavEvent = { event ->
      when(event) {
        is MainScreenNavEvent.Portfolio.CareerDetail -> {
          navController.navigate(CareerDetailScreenRoute(event.id))
        }
        is MainScreenNavEvent.Portfolio.CareerList -> {
          navController.navigate(CareerListScreenRoute)
        }
        is MainScreenNavEvent.Portfolio.GitHub -> {
          navController.navigate(WebviewScreenRoute(title = "GitHub", event.url))
        }

        is MainScreenNavEvent.Playground.ToyDetail -> {
          navController.navigate(ToyDetailScreenRoute(event.id))
        }
        is MainScreenNavEvent.Playground.ToyList -> {
          navController.navigate(ToyListScreenRoute)
        }
      }
    },
    modifier = Modifier.fillMaxSize(),
  )
}