package com.jiy.ui.modifier

import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import com.valentinilk.shimmer.shimmer

fun Modifier.shimmer(): Modifier = this
  .composed { shimmer() }