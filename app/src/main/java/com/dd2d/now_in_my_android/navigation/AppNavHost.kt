package com.dd2d.now_in_my_android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.dd2d.json_placeholder.album.presentation.detail.AlbumDetailScreenRoute
import com.dd2d.json_placeholder.post.presentation.detail.PostDetailScreenRoute
import com.dd2d.maps.google_map.presentation.place_search.PlaceSearchScreenRoute
import com.dd2d.now_in_my_android.navigation.module.jsonPlaceholder
import com.dd2d.now_in_my_android.navigation.module.maps
import com.dd2d.now_in_my_android.presentation.main.MainScreenRoute
import com.dd2d.now_in_my_android.presentation.main.model.MainScreenNavEvent
import com.dd2d.now_in_my_android.presentation.main.routeMainScreen

@Composable
fun AppNavHost(
  modifier: Modifier = Modifier,
  navController: NavHostController = rememberNavController(),
) {
  NavHost(
    navController = navController,
    startDestination = MainScreenRoute(),
    modifier = modifier
  ) {
    routeMainScreen(
      onNavEvent = { event ->
        when(event) {
          is MainScreenNavEvent.PostDetail -> navController.navigate(PostDetailScreenRoute(event.postId))
          is MainScreenNavEvent.AlbumDetail -> navController.navigate(AlbumDetailScreenRoute(event.albumId))
          is MainScreenNavEvent.PlaceSearch -> navController.navigate(PlaceSearchScreenRoute)
        }
      }
    )
    jsonPlaceholder(navController)
    maps(navController)
  }
}