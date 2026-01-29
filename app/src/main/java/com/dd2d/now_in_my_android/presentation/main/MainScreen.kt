package com.dd2d.now_in_my_android.presentation.main

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.dd2d.json_placeholder.album.presentation.list.AlbumListScreenRoute
import com.dd2d.json_placeholder.album.presentation.list.routeAlbumListScreen
import com.dd2d.json_placeholder.post.presentation.list.PostListScreenRoute
import com.dd2d.json_placeholder.post.presentation.list.routePostListScreen
import com.dd2d.maps.google_map.presentation.map.GoogleMapScreenRoute
import com.dd2d.maps.google_map.presentation.map.routeGoogleMapScreen
import com.dd2d.now_in_my_android.presentation.main.component.BottomNavBar
import com.dd2d.now_in_my_android.presentation.main.model.MainScreenBottomNavItem
import com.dd2d.now_in_my_android.presentation.main.model.MainScreenNavEvent

@Composable
fun MainScreen(
  onNavEvent: (MainScreenNavEvent) -> Unit,
  modifier: Modifier = Modifier,
  viewModel: MainViewModel = hiltViewModel()
) {
  val navController = rememberNavController()
  val selectedItem by viewModel.selectedItemState.collectAsStateWithLifecycle()

  LaunchedEffect(selectedItem) {
    val nextRoute = when(selectedItem) {
      MainScreenBottomNavItem.Post -> PostListScreenRoute
      MainScreenBottomNavItem.Album -> AlbumListScreenRoute
      MainScreenBottomNavItem.Maps -> GoogleMapScreenRoute
    }

    navController.navigate(nextRoute) {
      launchSingleTop = true
      restoreState = true
      popUpTo(navController.graph.id) {
        saveState = true
      }
    }
  }

  Scaffold(
    bottomBar = {
      BottomNavBar(
        selectedItem = selectedItem,
        onItemClick = viewModel::chaneItem,
        modifier = Modifier.fillMaxWidth(),
      )
    },
    modifier = modifier
  ) { inner ->
    NavHost(
      navController = navController,
      startDestination = PostListScreenRoute,
      modifier = Modifier
        .consumeWindowInsets(inner)
        .fillMaxSize()
        .padding(inner)
    ) {
      routePostListScreen(
        onPostClick = { onNavEvent(MainScreenNavEvent.PostDetail(it)) }
      )
      routeAlbumListScreen(
        onAlbumClick = { onNavEvent(MainScreenNavEvent.AlbumDetail(it)) }
      )
      routeGoogleMapScreen(onPlaceSearchClick = { /* TODO */ })
    }
  }
}