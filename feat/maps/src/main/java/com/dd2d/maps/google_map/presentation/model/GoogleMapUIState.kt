package com.dd2d.maps.google_map.presentation.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class GoogleMapUIState {
  var isLocationPermissionGranted by mutableStateOf<Boolean?>(null); internal set
}