package com.dd2d.maps.google_map.presentation.component

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.dd2d.maps.R

@Composable
internal fun GoogleMapScreenTopBar(
  onBack: () -> Unit,
  containerColor: Color,
  modifier: Modifier = Modifier
) {
  TopAppBar(
    title = {},
    navigationIcon = {
      IconButton(onClick = onBack) {
        Icon(
          imageVector = ImageVector.vectorResource(R.drawable.left),
          contentDescription = "뒤로가기"
        )
      }
    },
    colors = TopAppBarDefaults.topAppBarColors(
      containerColor = containerColor,
    ),
    modifier = modifier
  )
}