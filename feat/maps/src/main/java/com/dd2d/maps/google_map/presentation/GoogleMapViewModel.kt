package com.dd2d.maps.google_map.presentation

import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dd2d.core.flow.stateInWhileSubscribed
import com.dd2d.core.stateful.Stateful
import com.dd2d.core.stateful.statefulFlow
import com.dd2d.maps.google_map.domain.PlaceRepository
import com.dd2d.maps.google_map.presentation.model.GoogleMapUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class GoogleMapViewModel @Inject constructor(
  placeRepository: PlaceRepository,
) : ViewModel() {
  val googleMapUIState = GoogleMapUIState()

  val currentLocationState = snapshotFlow { googleMapUIState.isLocationPermissionGranted }
    .filterNotNull()
    .flatMapLatest { isLocationPermissionGranted ->
      statefulFlow {
        when(isLocationPermissionGranted) {
          false -> throw SecurityException()
          true -> placeRepository.getCurrentLocation()
        }
      }
    }
    .stateInWhileSubscribed(scope = viewModelScope, initialValue = Stateful.Loading)


  private val placeId = MutableStateFlow<String?>(null)
  fun fetchPlace(id: String) { placeId.value = id }
  fun clearPlaceState() { placeId.value = null }
  val placeState = placeId
    .flatMapLatest { id ->
      when(id) {
        null -> flowOf(null)
        else -> statefulFlow { placeRepository.getPlace(id) }
      }
    }
    .stateInWhileSubscribed(scope = viewModelScope, initialValue = null)
}