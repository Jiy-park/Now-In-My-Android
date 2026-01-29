package com.dd2d.maps.google_map.presentation.map.component

import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
internal fun GoogleMapScreenTopBar(
  containerColor: Color,
  modifier: Modifier = Modifier
) {
  TopAppBar(
    title = {},
    colors = TopAppBarDefaults.topAppBarColors(
      containerColor = containerColor,
    ),
    modifier = modifier
  )
}