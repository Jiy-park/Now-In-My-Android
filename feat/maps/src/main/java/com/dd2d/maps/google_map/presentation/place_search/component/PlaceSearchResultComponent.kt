package com.dd2d.maps.google_map.presentation.place_search.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.dd2d.maps.google_map.domain.model.PlaceSearchResult

@Composable
internal fun PlaceSearchResultComponent(
  result: PlaceSearchResult,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  contentPadding: PaddingValues = PaddingValues()
) {
  Column(
    modifier = modifier
      .clickable(onClick = onClick)
      .padding(contentPadding)
  ) {
    Text(
      text = result.name,
      fontWeight = FontWeight.Normal,
      color = MaterialTheme.colorScheme.onSurface,
      fontSize = 16.sp,
      lineHeight = 1.4.em,
    )
    Text(
      text = result.address,
      fontWeight = FontWeight.Normal,
      color = MaterialTheme.colorScheme.onSurfaceVariant,
      fontSize = 14.sp,
      lineHeight = 1.2.em,
    )
  }
}