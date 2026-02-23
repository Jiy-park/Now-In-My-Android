@file:OptIn(ExperimentalLayoutApi::class)

package com.dd2d.maps.google_map.presentation.map

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import com.dd2d.core.stateful.Stateful
import com.dd2d.maps.google_map.domain.model.Place
import com.dd2d.maps.google_map.domain.model.PlaceSearchResult
import com.dd2d.maps.google_map.presentation.map.component.GoogleMapScreenTopBar
import com.dd2d.maps.google_map.presentation.map.content.ErrorContent
import com.dd2d.maps.google_map.presentation.map.content.GoogleMapContent
import com.dd2d.maps.google_map.presentation.map.content.LoadingContent
import com.dd2d.maps.google_map.presentation.place_search.component.PlaceSearchResultComponent
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.ktx.model.cameraPosition
import kotlinx.coroutines.launch
import org.checkerframework.checker.units.qual.s

@Composable
fun GoogleMapScreen(
  onBack: () -> Unit,
  onPlaceSearchClick: () -> Unit,
  modifier: Modifier = Modifier,
  viewModel: GoogleMapViewModel = hiltViewModel()
) {
  val context = LocalContext.current
  val density = LocalDensity.current
  val keyboard = LocalSoftwareKeyboardController.current
  val isImeVisible = WindowInsets.isImeVisible

  val currentLocationState by viewModel.currentLocationState.collectAsStateWithLifecycle()
  val placeState by viewModel.placeState.collectAsStateWithLifecycle()
  val searchResultState by viewModel.searchResultState.collectAsStateWithLifecycle()

  var searchFieldHeight by remember { mutableStateOf(0.dp) }
  val scope = rememberCoroutineScope()
  val cameraPositionState = rememberCameraPositionState {
    position = cameraPosition {
      target(LatLng(37.5668, 126.9784))
      zoom(15F)
    }
  }
  var markerState by remember { mutableStateOf<MarkerState?>(null) }

  LaunchedEffect(Unit) {
    viewModel.googleMapUIState.isLocationPermissionGranted =
      context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
  }

  val bottomSheetState = rememberStandardBottomSheetState(initialValue = SheetValue.Hidden, skipHiddenState = false)
  val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState)

  LaunchedEffect(markerState) {
    if(markerState != null) {
      scaffoldState.bottomSheetState.expand()
    }
  }

  LaunchedEffect(searchResultState) {
    if(searchResultState.isNotEmpty()) {
      scaffoldState.bottomSheetState.expand()
    }
  }

  LaunchedEffect(placeState) {
    when(val state = placeState) {
      null -> { /*scaffoldState.bottomSheetState.hide()*/ }
      is Stateful.Loading -> {
        keyboard?.hide()
        scaffoldState.bottomSheetState.partialExpand()
      }
      is Stateful.Error -> {}
      is Stateful.Success -> {
        state.data.location?.let { placeLocation ->
          val location = LatLng(placeLocation.latitude, placeLocation.longitude)
          val newCameraPosition = cameraPosition {
            target(location)
            zoom(20F)
          }
          val update = CameraUpdateFactory.newCameraPosition(newCameraPosition)
          markerState = MarkerState(position = location)
          cameraPositionState.animate(update)
        }
      }
    }
  }

  Box(modifier = modifier) {
    BottomSheetScaffold(
      scaffoldState = scaffoldState,
      sheetContent = {
        GoogleMapScreenTopBar(
          onBack = {
            if(isImeVisible) keyboard?.hide()
            else onBack()
          },
          searchTextState = viewModel.searchTextState,
          isImeVisible = isImeVisible,
          isSearchMode = placeState == null,
          onSearchModeToggleRequest = viewModel::clearPlaceState,
          modifier = Modifier
            .navigationBarsPadding()
            .imePadding()
            .onSizeChanged {
              searchFieldHeight = with(density) { it.height.toDp() }
            }
            .zIndex(1F)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
        )
        Column(
          verticalArrangement = Arrangement.Center,
          horizontalAlignment = Alignment.CenterHorizontally,
          modifier = Modifier
            .navigationBarsPadding()
        ) {
          placeState?.let { state ->
            PlaceComponent(
              placeState = state,
              modifier = Modifier
                .statusBarsPadding()
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(200.dp)
                .verticalScroll(rememberScrollState())
            )
          }
          SearchResultComponent(
            results = searchResultState,
            onResultClick = { viewModel.fetchPlace(it.id) },
            contentPadding = PaddingValues(bottom = searchFieldHeight),
            modifier = Modifier
              .statusBarsPadding()
              .heightIn(max = 200.dp)
              .fillMaxWidth()
          )
        }
      },
      sheetDragHandle = null,
      sheetSwipeEnabled = false,
      modifier = modifier
        .navigationBarsPadding()
        .imePadding()

    ) { inner ->
      Box(
        modifier = Modifier
          .consumeWindowInsets(inner)
          .fillMaxSize()
      ) {
        when(val state = currentLocationState) {
          is Stateful.Loading -> LoadingContent(modifier = Modifier.padding(inner))
          is Stateful.Error -> {
            ErrorContent(
              throwable = state.exception,
              onPermissionGranted = { viewModel.googleMapUIState.isLocationPermissionGranted = true },
              modifier = Modifier.padding(inner)
            )
          }
          is Stateful.Success -> {
            GoogleMapContent(
              cameraPositionState = cameraPositionState,
              markerState = markerState,
              onPlaceClick = viewModel::fetchPlace,
              contentPadding = inner,
              onMarkerClick = {
                scope.launch {
                  with(scaffoldState.bottomSheetState) {
                    when(currentValue) {
                      SheetValue.Hidden -> partialExpand()
                      SheetValue.Expanded -> hide()
                        SheetValue.PartiallyExpanded -> expand()
                    }
                  }
                }
              },
              modifier = Modifier
                .fillMaxSize()
            )
          }
        }
      }
    }
  }
}

@Composable
private fun SearchResultComponent(
  results: List<PlaceSearchResult>,
  onResultClick: (PlaceSearchResult) -> Unit,
  contentPadding: PaddingValues,
  modifier: Modifier = Modifier
) {
  LazyColumn(
    overscrollEffect = null,
    verticalArrangement = Arrangement.Bottom,
    contentPadding = contentPadding,
    modifier = modifier
  ) {
    items(items = results, key = PlaceSearchResult::id) { item ->
      PlaceSearchResultComponent(
        result = item,
        onClick = { onResultClick(item) },
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = Modifier
          .fillMaxWidth()
      )
    }
  }
}

@Composable
private fun PlaceComponent(
  placeState: Stateful<Place>,
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current
  Column(modifier = modifier) {
    when(placeState) {
      is Stateful.Loading -> {
        Box(
          contentAlignment = Alignment.Center,
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp)
        ) {
          CircularProgressIndicator(
            strokeWidth = 2.dp,
            modifier = Modifier.size(20.dp)
          )
        }
      }
      is Stateful.Error -> {
        Box(
          contentAlignment = Alignment.Center,
          modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
        ) {
          Text(text = placeState.exception.localizedMessage?: "오류")
        }
      }
      is Stateful.Success -> {
        placeState.data.name?.let { name ->
          Text(text = name)
        }
        placeState.data.openNow?.let { openNow ->
          Text(text = if(openNow) "영업중" else "닫힘")
        }
        placeState.data.weekdayDescriptions?.forEach { description ->
          Text(text = description)
        }
        placeState.data.rating?.let { rating ->
          Text(
            text = buildString {
              append(rating)
              placeState.data.userRatingCount?.let { userRatingCount ->
                append("($userRatingCount)")
              }
            }
          )
        }
        placeState.data.contact?.let { contact ->
          Text(text = contact)
        }
        placeState.data.websiteUrl?.let { websiteUrl ->
          Text(text = websiteUrl)
        }
        placeState.data.googleMapUri?.let { googleMapUri ->
          Text(text = googleMapUri)
        }
        placeState.data.fullAddress?.let { fullAddress ->
          Text(text = fullAddress)
        }
        placeState.data.location?.let { location ->
          Text(text = location.toString())
        }
        FlowRow(
          horizontalArrangement = Arrangement.spacedBy(10.dp),
          verticalArrangement = Arrangement.spacedBy(10.dp),
          modifier = Modifier.fillMaxWidth()
        ) {
          placeState.data.types.forEach { type ->
            Text(text = type)
          }
        }
        Row(
          horizontalArrangement = Arrangement.spacedBy(10.dp),
          modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
        ) {
          val imageSize = 300
          placeState.data.images.forEachIndexed { index, image ->
            AsyncImage(
              model = ImageRequest.Builder(context)
                .data(image.url(size = imageSize))
                .diskCachePolicy(CachePolicy.DISABLED)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .memoryCacheKey("${image.placeId}/image/$index/&size=$imageSize")
                .size(imageSize)
                .build(),
              contentDescription = null,
              contentScale = ContentScale.Crop,
              modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .width(100.dp)
                .aspectRatio(1F)
            )
          }
        }
      }
    }
  }
}