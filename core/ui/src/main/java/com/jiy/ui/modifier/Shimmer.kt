package com.jiy.ui.modifier

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import com.valentinilk.shimmer.shimmer

fun Modifier.shimmer(): Modifier = this
  .composed { shimmer() }
  .background(Color.LightGray)