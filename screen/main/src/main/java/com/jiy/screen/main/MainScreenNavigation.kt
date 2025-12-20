package com.jiy.screen.main

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jiy.screen.main.model.MainScreenNavEvent
import com.jiy.screen.main.model.MainScreenTab
import com.jiy.ui.navigation.ScreenRoute
import kotlinx.serialization.Serializable

@Serializable
data class MainScreenRoute(val initialTab: MainScreenTab = MainScreenTab.Portfolio) : ScreenRoute

fun NavGraphBuilder.routeMainScreen(
  onNavEvent: (MainScreenNavEvent) -> Unit,
  modifier: Modifier = Modifier,
) {
  composable<MainScreenRoute> {
    MainScreen(
      onNavEvent = onNavEvent,
      modifier = modifier,
    )
  }
}