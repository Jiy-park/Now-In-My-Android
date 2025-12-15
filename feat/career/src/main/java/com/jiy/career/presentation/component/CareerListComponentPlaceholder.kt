package com.jiy.career.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jiy.ui.modifier.shimmer
import com.jiy.ui.theme.BorderColor
import com.jiy.ui.theme.Shape
import com.jiy.ui.theme.Spacing
import com.jiy.ui.theme.SurfaceColor
import kotlin.random.Random

@Composable
@Preview
fun CareerListComponentPlaceholder(
  modifier: Modifier = Modifier,
) {
  Column(
    verticalArrangement = Arrangement.spacedBy(Spacing.Medium),
    modifier = modifier
  ) {
    repeat(3) {
      Row(
        horizontalArrangement = Arrangement.spacedBy(Spacing.Medium),
        modifier = Modifier
          .clip(Shape.Card)
          .fillMaxWidth()
          .background(SurfaceColor)
          .border(width = 1.dp, color = BorderColor, shape = Shape.Card)
          .padding(Spacing.Medium)
      ) {
        Box(
          modifier = Modifier
            .clip(Shape.Card)
            .size(64.dp)
            .shimmer()
        )
        Column(
          verticalArrangement = Arrangement.spacedBy(Spacing.Small),
          modifier = Modifier
            .fillMaxWidth()
        ) {
          Box(
            modifier = Modifier
              .fillMaxWidth(Random.nextFloat().coerceIn(0.3F..1F))
              .height(20.dp)
              .shimmer()
          )
          Box(
            modifier = Modifier
              .fillMaxWidth(Random.nextFloat().coerceIn(0.3F..0.5F))
              .height(18.dp)
              .shimmer()
          )
          Box(
            modifier = Modifier
              .fillMaxWidth(Random.nextFloat().coerceIn(0.3F..0.5F))
              .height(16.dp)
              .shimmer()
          )
        }
      }
    }
  }
}