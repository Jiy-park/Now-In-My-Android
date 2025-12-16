package com.jiy.screen.career.list

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jiy.ui.navigation.ScreenRoute
import kotlinx.serialization.Serializable

@Serializable
data object CareerListScreenRoute : ScreenRoute

fun NavGraphBuilder.routeCareerListScreen(
  onBack: () -> Unit,
  onCareerClick: (id: Int) -> Unit,
  modifier: Modifier = Modifier,
) {
  composable<CareerListScreenRoute> {
    CareerListScreen(
      onBack = onBack,
      onCareerClick = onCareerClick,
      modifier = modifier,
    )
  }
}