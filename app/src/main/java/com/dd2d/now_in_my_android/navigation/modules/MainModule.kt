package com.dd2d.now_in_my_android.navigation.modules

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.jiy.screen.main.model.MainScreenNavEvent
import com.jiy.screen.main.routeMainScreen

internal fun NavGraphBuilder.mainModule(
  navController: NavHostController,
) {
  routeMainScreen(
    onNavEvent = { event ->
      when(event) {
        is MainScreenNavEvent.GitHub -> { /** 내부 웹뷰로 깃헙 띄우기 */ }
        is MainScreenNavEvent.CareerDetail -> { /** 경력 상세 화면으로 */ }
        is MainScreenNavEvent.CareerList -> { /** 경력 목록 화면으로 */ }
      }
    },
    modifier = Modifier.fillMaxSize(),
  )
}