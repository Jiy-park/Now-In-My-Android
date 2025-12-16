package com.jiy.screen.career.detail.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jiy.ui.component.ShimmerBox
import com.jiy.ui.theme.Padding
import com.jiy.ui.theme.Shape
import com.jiy.ui.theme.Spacing

@Composable
@Preview
internal fun CareerDetailLoadingContent(
  modifier: Modifier = Modifier.fillMaxSize().background(Color.White)
) {
  Column(
    verticalArrangement = Arrangement.spacedBy(Spacing.Large),
    modifier = modifier
      .fillMaxSize()
      .padding(Padding.Screen.Inset)
  ) {
    Row(
      horizontalArrangement = Arrangement.spacedBy(Padding.Screen.Horizontal),
      modifier = Modifier
        .fillMaxWidth()
        .height(64.dp)
    ) {
      ShimmerBox(shape = Shape.Card, size = 64.dp)
      Column(verticalArrangement = Arrangement.spacedBy(Spacing.ExtraSmall)) {
        ShimmerBox(Modifier.fillMaxWidth(0.8F).height(24.dp))
        ShimmerBox(Modifier.fillMaxWidth(0.55F).height(16.dp))
        ShimmerBox(Modifier.fillMaxWidth(0.4F).height(16.dp))
      }
    }

    ShimmerBox(shape = Shape.Card, modifier = Modifier.fillMaxWidth().height(150.dp))

    Column(
      verticalArrangement = Arrangement.spacedBy(Spacing.Small),
    ) {
      ShimmerBox(width = 100.dp, height = 24.dp)
      FlowRow(
        horizontalArrangement = Arrangement.spacedBy(Spacing.Small),
        verticalArrangement = Arrangement.spacedBy(Spacing.Small),
        modifier = Modifier.fillMaxWidth()
      ) {
        repeat((6..8).random()) {
          ShimmerBox(shape = Shape.Chip, width = (50..150).random().dp, height = 24.dp)
        }
      }
    }

    Column(
      verticalArrangement = Arrangement.spacedBy(Spacing.Small),
    ) {
      ShimmerBox(width = 100.dp, height = 24.dp)
      repeat(3) {
        ShimmerBox(shape = Shape.Card, modifier = Modifier.fillMaxWidth().height(100.dp))
      }
    }
  }
}