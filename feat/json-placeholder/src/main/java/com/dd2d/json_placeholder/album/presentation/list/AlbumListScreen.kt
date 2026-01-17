package com.dd2d.json_placeholder.album.presentation.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dd2d.json_placeholder._core.ui.content.StatefulContent
import com.dd2d.json_placeholder.album.domain.model.Album
import com.dd2d.json_placeholder.album.presentation.list.component.AlbumListComponent

@Composable
fun AlbumListScreen(
  onAlbumClick: (albumId: Int) -> Unit,
  modifier: Modifier = Modifier,
  viewModel: AlbumListViewModel = hiltViewModel()
) {
  val albumListState by viewModel.albumListState.collectAsStateWithLifecycle()

  Scaffold(modifier = modifier) { inner ->
    StatefulContent(
      state = albumListState,
      errorMessage = { "앨범 목록을 불러오지 못했습니다." },
      modifier = Modifier
        .consumeWindowInsets(inner)
        .fillMaxSize()
        .padding(inner)
    ) { albumList ->
      AlbumListContent(
        albumList = albumList,
        onAlbumClick = onAlbumClick,
        contentPadding = PaddingValues(vertical = 20.dp),
        modifier = Modifier
          .fillMaxSize()
      )
    }
  }
}

@Composable
private fun AlbumListContent(
  albumList: List<Album>,
  onAlbumClick: (id: Int) -> Unit,
  modifier: Modifier = Modifier,
  contentPadding: PaddingValues = PaddingValues()
) {
  Column(modifier = modifier) {
    LazyColumn(
      verticalArrangement = Arrangement.spacedBy(16.dp),
      contentPadding = contentPadding,
      modifier = Modifier.fillMaxSize()
    ) {
      items(items = albumList, key = Album::id) { item ->
        AlbumListComponent(
          album = item,
          onClick = onAlbumClick,
          modifier = Modifier
            .animateItem()
            .fillMaxWidth()
        )
      }
    }
  }
}