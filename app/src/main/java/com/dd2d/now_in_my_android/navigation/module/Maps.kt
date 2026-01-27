package com.dd2d.now_in_my_android.navigation.module

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.dd2d.maps.google_map.presentation.routeGoogleMapScreen

fun NavGraphBuilder.maps(navController: NavHostController) {
  routeGoogleMapScreen()
}