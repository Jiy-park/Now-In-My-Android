package com.dd2d.maps.google_map.presentation.map

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object GoogleMapScreenRoute

fun NavGraphBuilder.routeGoogleMapScreen(
  onBack: () -> Unit,
  onPlaceSearchClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  composable<GoogleMapScreenRoute> {
    GoogleMapScreen(
      onBack = onBack,
      onPlaceSearchClick = onPlaceSearchClick,
      modifier = modifier,
    )
  }
}