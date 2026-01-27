package com.dd2d.now_in_my_android.presentation.main

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dd2d.now_in_my_android.presentation.main.model.MainScreenBottomNavItem
import com.dd2d.now_in_my_android.presentation.main.model.MainScreenNavEvent
import kotlinx.serialization.Serializable

@Serializable
data class MainScreenRoute(val initialItemIndex: Int = MainScreenBottomNavItem.Post.ordinal)

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