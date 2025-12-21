
package com.jiy.screen.toy.detail

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jiy.ui.navigation.ScreenRoute
import kotlinx.serialization.Serializable

@Serializable
data class ToyDetailScreenRoute(val id: Int) : ScreenRoute

fun NavGraphBuilder.routeToyDetailScreen(
  onBack: () -> Unit,
  modifier: Modifier = Modifier,
) {
  composable<ToyDetailScreenRoute> {
    ToyDetailScreen(
      onBack = onBack,
      onMoreVersionClick = {},
      modifier = modifier,
    )
  }
}