package com.dd2d.json_placeholder.album.presentation.detail


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.dd2d.core.flow.stateInWhileSubscribed
import com.dd2d.core.stateful.Stateful
import com.dd2d.core.stateful.statefulFlow
import com.dd2d.json_placeholder.album.domain.AlbumRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlbumDetailViewModel @Inject constructor(
  savedStateHandle: SavedStateHandle,
  albumRepository: AlbumRepository,
) : ViewModel() {
  val albumDetailScreenRoute = savedStateHandle.toRoute<AlbumDetailScreenRoute>()

  /** 앨범 상세 상태 */
  val albumDetailState = statefulFlow { albumRepository.getAlbum(albumDetailScreenRoute.albumId) }
    .stateInWhileSubscribed(scope = viewModelScope, initialValue = Stateful.Loading)

  /** 앨범 사진 목록 상태 */
  val albumPhotoListState = statefulFlow { albumRepository.getAlbumPhotoList(albumDetailScreenRoute.albumId) }
    .stateInWhileSubscribed(scope = viewModelScope, initialValue = Stateful.Loading)
}