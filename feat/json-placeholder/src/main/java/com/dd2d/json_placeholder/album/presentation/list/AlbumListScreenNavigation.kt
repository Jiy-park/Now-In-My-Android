package com.dd2d.json_placeholder.album.presentation.list

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object AlbumListScreenRoute

fun NavGraphBuilder.routeAlbumListScreen(
  onAlbumClick: (albumId: Int) -> Unit,
  modifier: Modifier = Modifier,
) {
  composable<AlbumListScreenRoute> {
    AlbumListScreen(
      onAlbumClick = onAlbumClick,
      modifier = modifier,
    )
  }
}