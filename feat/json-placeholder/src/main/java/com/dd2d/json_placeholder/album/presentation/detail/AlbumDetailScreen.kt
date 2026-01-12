package com.dd2d.json_placeholder.album.presentation.detail

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dd2d.core.stateful.Stateful
import com.dd2d.json_placeholder._core.ui.component.ClickableAuthorComponent
import com.dd2d.json_placeholder._core.ui.component.TopBar
import com.dd2d.json_placeholder._core.ui.content.StatefulContent
import com.dd2d.json_placeholder.album.domain.model.Album
import com.dd2d.json_placeholder.album.domain.model.AlbumPhoto
import com.dd2d.json_placeholder.album.presentation.detail.component.AlbumPhotoListComponent

@Composable
fun AlbumDetailScreen(
  onBack: () -> Unit,
  onAlbumAuthorClick: (authorId: Int) -> Unit,
  modifier: Modifier = Modifier,
  viewModel: AlbumDetailViewModel = hiltViewModel()
) {
  val albumDetailState by viewModel.albumDetailState.collectAsStateWithLifecycle()
  val albumPhotoListState by viewModel.albumPhotoListState.collectAsStateWithLifecycle()

  BackHandler(onBack = onBack)
  Scaffold(
    topBar = { TopBar(title = "Album 상세", onBack = onBack) },
    modifier = modifier
  ) { inner ->
    StatefulContent(
      state = albumDetailState,
      errorMessage = { "Album을 불러오지 못했습니다." },
      modifier = Modifier
        .consumeWindowInsets(inner)
        .fillMaxSize()
        .padding(inner)
    ) { albumDetail ->
      AlbumDetailContent(
        albumDetail = albumDetail,
        onAlbumAuthorClick = onAlbumAuthorClick,
        albumPhotoListState = albumPhotoListState,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 20.dp),
        modifier = Modifier.fillMaxSize()
      )
    }
  }
}

@Composable
private fun AlbumDetailContent(
  albumDetail: Album,
  onAlbumAuthorClick: (id: Int) -> Unit,
  albumPhotoListState: Stateful<List<AlbumPhoto>>,
  modifier: Modifier = Modifier,
  contentPadding: PaddingValues = PaddingValues()
) {
  Column(
    modifier = modifier
      .padding(contentPadding)
  ) {
    ClickableAuthorComponent(
      authorNickname = albumDetail.author.nickname,
      profileImageUrl = albumDetail.author.profileImageUrl,
      onClick = { onAlbumAuthorClick(albumDetail.author.id) },
      contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
    )
    Text(
      text = albumDetail.title,
      fontWeight = FontWeight.W600,
      color = MaterialTheme.colorScheme.onSurface,
      fontSize = 18.sp,
      lineHeight = 1.4.em,
    )
    HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
    StatefulContent(
      state = albumPhotoListState,
      errorMessage = { "AlbumPhoto를 불러오지 못했습니다." },
      modifier = Modifier
        .weight(1F)
    ) { albumPhotoList ->
      AlbumPhotoListComponent(
        albumPhotoList = albumPhotoList,
        modifier = Modifier.fillMaxSize(),
      )
    }
  }
}