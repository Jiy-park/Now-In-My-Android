package com.dd2d.json_placeholder.album.presentation.list


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dd2d.core.flow.stateInWhileSubscribed
import com.dd2d.core.stateful.Stateful
import com.dd2d.core.stateful.statefulFlow
import com.dd2d.json_placeholder.album.domain.AlbumRepository
import com.dd2d.json_placeholder.album.domain.model.Album
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AlbumListViewModel @Inject constructor(
  albumRepository: AlbumRepository
) : ViewModel() {
  val albumListState: StateFlow<Stateful<List<Album>>> = statefulFlow { albumRepository.getAlbumList(authorId = null) }
    .stateInWhileSubscribed(scope = viewModelScope, initialValue = Stateful.Loading)
}