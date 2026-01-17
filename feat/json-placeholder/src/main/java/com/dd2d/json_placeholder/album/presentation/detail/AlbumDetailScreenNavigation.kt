package com.dd2d.json_placeholder.album.presentation.detail

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class AlbumDetailScreenRoute(val albumId: Int)

fun NavGraphBuilder.routeAlbumDetailScreen(
  onBack: () -> Unit,
  onAlbumAuthorClick: (authorId: Int) -> Unit,
  modifier: Modifier = Modifier,
) {
  composable<AlbumDetailScreenRoute> {
    AlbumDetailScreen(
      onBack = onBack,
      onAlbumAuthorClick = onAlbumAuthorClick,
      modifier = modifier,
    )
  }
}