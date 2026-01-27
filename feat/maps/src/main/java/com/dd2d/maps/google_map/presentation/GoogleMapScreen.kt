package com.dd2d.maps.google_map.presentation

import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dd2d.core.stateful.Stateful
import com.dd2d.maps.google_map.presentation.component.GoogleMapScreenTopBar
import com.dd2d.maps.google_map.presentation.component.PlaceBottomSheet
import com.dd2d.maps.google_map.presentation.content.ErrorContent
import com.dd2d.maps.google_map.presentation.content.GoogleMapContent
import com.dd2d.maps.google_map.presentation.content.LoadingContent
import kotlinx.coroutines.launch

@Composable
fun GoogleMapScreen(
  modifier: Modifier = Modifier,
  viewModel: GoogleMapViewModel = hiltViewModel()
) {
  val context = LocalContext.current

  val currentLocationState by viewModel.currentLocationState.collectAsStateWithLifecycle()
  val placeState by viewModel.placeState.collectAsStateWithLifecycle()

  val scope = rememberCoroutineScope()
  val placeSheetState = rememberModalBottomSheetState()

  LaunchedEffect(Unit) {
    viewModel.googleMapUIState.isLocationPermissionGranted =
      context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
  }


  Scaffold(
    topBar = {
      GoogleMapScreenTopBar(
        containerColor = when(currentLocationState) {
          is Stateful.Loading -> MaterialTheme.colorScheme.surface
          is Stateful.Error -> MaterialTheme.colorScheme.surface
          is Stateful.Success -> Color.Transparent
        }
      )
    },
    modifier = modifier
  ) { inner ->
    when(val state = currentLocationState) {
      is Stateful.Loading -> {
        LoadingContent(
          modifier = Modifier
            .consumeWindowInsets(inner)
            .fillMaxSize()
            .padding(inner)
        )
      }
      is Stateful.Error -> {
        ErrorContent(
          throwable = state.exception,
          onPermissionGranted = { viewModel.googleMapUIState.isLocationPermissionGranted = true },
          modifier = Modifier
            .consumeWindowInsets(inner)
            .fillMaxSize()
            .padding(inner)
        )
      }
      is Stateful.Success -> {
        GoogleMapContent(
          currentLocation = state.data,
          onPlaceClick = viewModel::fetchPlace,
          contentPadding = inner,
          modifier = Modifier
            .consumeWindowInsets(inner)
            .fillMaxSize()
        )
      }
    }
  }
  placeState?.let { state ->
    PlaceBottomSheet(
      onDismissRequest = {
        scope.launch {
          placeSheetState.hide()
          viewModel.clearPlaceState()
        }
      },
      placeState = state,
      state = placeSheetState,
    )
  }
}