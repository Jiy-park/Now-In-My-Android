package com.jiy.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import com.jiy.ui.modifier.shimmer

@Composable
fun ShimmerBox(
  shape: Shape,
  size: Dp,
  modifier: Modifier = Modifier
) {
  Box(
    modifier = modifier
      .clip(shape)
      .size(size)
      .shimmer()
  )
}

@Composable
fun ShimmerBox(
  shape: Shape,
  width: Dp,
  height: Dp,
  modifier: Modifier = Modifier
) {
  Box(
    modifier = modifier
      .clip(shape)
      .size(width, height)
      .shimmer()
  )
}