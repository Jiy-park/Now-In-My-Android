package com.jiy.screen.toy.list

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jiy.ui.navigation.ScreenRoute
import kotlinx.serialization.Serializable

@Serializable
data object ToyListScreenRoute : ScreenRoute

fun NavGraphBuilder.routeToyListScreen(
  onBack: () -> Unit,
  onToyClick: (toyId: Int) -> Unit,
  modifier: Modifier = Modifier,
) {
  composable<ToyListScreenRoute> {
    ToyListScreen(
      onBack = onBack,
      onToyClick = onToyClick,
      modifier = modifier,
    )
  }
}