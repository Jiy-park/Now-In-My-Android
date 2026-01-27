package com.dd2d.maps.google_map.presentation.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dd2d.maps.google_map.domain.model.PlaceLocation
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.widgets.DisappearingScaleBar
import com.google.maps.android.ktx.model.cameraPosition

@Composable
internal fun GoogleMapContent(
  currentLocation: PlaceLocation,
  contentPadding: PaddingValues,
  onPlaceClick: (poiId: String) -> Unit,
  modifier: Modifier = Modifier
) {
  val cameraPositionState = rememberCameraPositionState {
    position = cameraPosition {
      target(LatLng(currentLocation.latitude, currentLocation.longitude))
      zoom(16F)
    }
  }
  val mapProperties = remember {
    MapProperties(
      isBuildingEnabled = true,
      isIndoorEnabled = true,
      isMyLocationEnabled = true,
      isTrafficEnabled = true,
    )
  }

  Box(modifier = modifier) {
    GoogleMap(
      cameraPositionState = cameraPositionState,
      properties = mapProperties,
      onPOIClick = { poi ->
        onPlaceClick(poi.placeId)
      },
      contentPadding = contentPadding,
      modifier = Modifier.matchParentSize(),
    ) {

    }
    DisappearingScaleBar(
      cameraPositionState = cameraPositionState,
      modifier = Modifier
        .align(Alignment.TopStart)
        .padding(contentPadding)
    )
  }
}
