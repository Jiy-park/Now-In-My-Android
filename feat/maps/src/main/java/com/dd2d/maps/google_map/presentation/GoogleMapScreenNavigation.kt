package com.dd2d.maps.google_map.presentation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object GoogleMapScreenRoute

fun NavGraphBuilder.routeGoogleMapScreen(
  modifier: Modifier = Modifier,
) {
  composable<GoogleMapScreenRoute> {
    GoogleMapScreen(
      modifier = modifier,
    )
  }
}