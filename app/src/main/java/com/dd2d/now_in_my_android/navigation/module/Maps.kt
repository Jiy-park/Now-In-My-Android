package com.dd2d.now_in_my_android.navigation.module

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.dd2d.maps.google_map.presentation.map.routeGoogleMapScreen
import com.dd2d.maps.google_map.presentation.place_search.PlaceSearchScreenRoute
import com.dd2d.maps.google_map.presentation.place_search.routePlaceSearchScreen

fun NavGraphBuilder.maps(navController: NavHostController) {
  routeGoogleMapScreen(
    onPlaceSearchClick = {
      navController.navigate(PlaceSearchScreenRoute)
    }
  )
  routePlaceSearchScreen(
    onBack = navController::popBackStack,
    onPlaceClick = {},
  )
}