package com.dd2d.maps.google_map.presentation.place_search

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dd2d.maps.google_map.domain.model.PlaceSearchResult
import com.dd2d.maps.google_map.presentation.place_search.component.PlaceSearchResultComponent
import com.dd2d.maps.google_map.presentation.place_search.component.PlaceSearchScreenTopBar

@Composable
fun PlaceSearchScreen(
  onBack: () -> Unit,
  onPlaceClick: (placeId: String) -> Unit,
  modifier: Modifier = Modifier,
  viewModel: PlaceSearchViewModel = hiltViewModel()
) {
  val searchResultState by viewModel.searchResultState.collectAsStateWithLifecycle()

  Scaffold(
    topBar = {
      PlaceSearchScreenTopBar(
        state = viewModel.searchTextState,
        onBackClick = onBack,
        modifier = Modifier
          .statusBarsPadding()
          .padding(top = 24.dp, bottom = 12.dp)
          .padding(horizontal = 16.dp)
          .fillMaxWidth()
      )
    },
    modifier = modifier
  ) { inner ->
    PlaceSearchContent(
      searchResultState = searchResultState,
      onPlaceClick = onPlaceClick,
      modifier = Modifier
        .consumeWindowInsets(inner)
        .fillMaxSize()
        .imePadding()
        .padding(inner)
    )
  }
}

@Composable
private fun PlaceSearchContent(
  searchResultState: List<PlaceSearchResult>,
  onPlaceClick: (id: String) -> Unit,
  modifier: Modifier = Modifier
) {
  LazyColumn(
    modifier = modifier,
  ) {
    items(
      items = searchResultState,
      key = PlaceSearchResult::id
    ) { item ->
      PlaceSearchResultComponent(
        result = item,
        onClick = { onPlaceClick(item.id) },
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = Modifier
          .animateItem()
          .fillMaxWidth()
      )
    }
  }
}