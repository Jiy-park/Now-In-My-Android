package com.dd2d.maps.google_map.presentation.map.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.widgets.DisappearingScaleBar

@Composable
internal fun GoogleMapContent(
  cameraPositionState: CameraPositionState,
  markerState: MarkerState?,
  contentPadding: PaddingValues,
  onPlaceClick: (poiId: String) -> Unit,
  onMarkerClick: (Marker) -> Unit,
  modifier: Modifier = Modifier
) {
  val mapProperties = remember {
    MapProperties(
      isBuildingEnabled = true,
      isIndoorEnabled = true,
      isMyLocationEnabled = true,
      isTrafficEnabled = true,
    )
  }

  val mapUiSetting = remember {
    MapUiSettings(
      compassEnabled = true,
      indoorLevelPickerEnabled = true,
      mapToolbarEnabled = false,
      myLocationButtonEnabled = true,
      rotationGesturesEnabled = true,
      scrollGesturesEnabled = true,
      scrollGesturesEnabledDuringRotateOrZoom = true,
      tiltGesturesEnabled = true,
      zoomControlsEnabled = false,
      zoomGesturesEnabled = true,
    )
  }

  Box(modifier = modifier) {
    GoogleMap(
      cameraPositionState = cameraPositionState,
      properties = mapProperties,
      uiSettings = mapUiSetting,
      onPOIClick = { poi ->
        onPlaceClick(poi.placeId)
      },
      contentPadding = contentPadding,
      modifier = Modifier.matchParentSize(),
    ) {
      markerState?.let {
        Marker(
          state = markerState,
          onClick = {
            onMarkerClick(it)
            false
          }
        )
      }
    }
    DisappearingScaleBar(
      cameraPositionState = cameraPositionState,
      modifier = Modifier
        .statusBarsPadding()
        .align(Alignment.TopStart)
        .padding(contentPadding)
    )
  }
}
