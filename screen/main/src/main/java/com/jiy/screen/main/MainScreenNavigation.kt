package com.jiy.screen.main


import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jiy.screen.main._core.ui.ScreenRoute
import com.jiy.screen.main.model.MainScreenNavEvent
import kotlinx.serialization.Serializable

@Serializable
data object MainScreenRoute : ScreenRoute

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