@file:OptIn(FlowPreview::class)

package com.dd2d.maps.google_map.presentation.place_search

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dd2d.core.flow.stateInWhileSubscribed
import com.dd2d.maps.google_map.domain.PlaceRepository
import com.dd2d.maps.google_map.domain.model.PlaceSearchOption
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

@HiltViewModel
class PlaceSearchViewModel @Inject constructor(
  placeRepository: PlaceRepository,
) : ViewModel() {
  val searchTextState = TextFieldState()

  val searchResultState = snapshotFlow { searchTextState.text.toString() }
    .map { it.trim() }
    .debounce(200)
    .mapLatest { text ->
      if(text.isEmpty()) emptyList()
      else placeRepository.searchPlacesBy(option = PlaceSearchOption(keyword = text))
    }
    .stateInWhileSubscribed(scope = viewModelScope, initialValue = emptyList())
}