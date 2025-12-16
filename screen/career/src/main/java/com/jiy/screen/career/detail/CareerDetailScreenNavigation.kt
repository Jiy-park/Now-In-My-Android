package com.jiy.screen.career.detail

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jiy.ui.navigation.ScreenRoute
import kotlinx.serialization.Serializable

@Serializable
data class CareerDetailScreenRoute(val careerId: Int) : ScreenRoute

fun NavGraphBuilder.routeCareerDetailScreen(
  onBack: () -> Unit,
  modifier: Modifier = Modifier,
) {
  composable<CareerDetailScreenRoute> {
    CareerDetailScreen(
      onBack = onBack,
      modifier = modifier,
    )
  }
}