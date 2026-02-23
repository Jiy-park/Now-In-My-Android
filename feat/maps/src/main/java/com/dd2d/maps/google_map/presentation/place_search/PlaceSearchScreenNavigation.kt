package com.dd2d.maps.google_map.presentation.place_search

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object PlaceSearchScreenRoute

fun NavGraphBuilder.routePlaceSearchScreen(
  onBack: () -> Unit,
  onPlaceClick: (placeId: String) -> Unit,
  modifier: Modifier = Modifier,
) {
  composable<PlaceSearchScreenRoute> {
    PlaceSearchScreen(
      onBack = onBack,
      onPlaceClick = onPlaceClick,
      modifier = modifier,
    )
  }
}