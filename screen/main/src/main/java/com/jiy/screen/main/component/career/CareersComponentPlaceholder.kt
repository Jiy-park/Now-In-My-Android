package com.jiy.screen.main.component.career

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jiy.screen.main.model.CardShape
import com.valentinilk.shimmer.shimmer

@Composable
internal fun CareersComponentPlaceholder(
  modifier: Modifier = Modifier
) {
  Column(
    verticalArrangement = Arrangement.spacedBy(6.dp),
    modifier = modifier
  ) {
    repeat(2) {
      Box(
        modifier = Modifier
          .clip(CardShape)
          .shimmer()
          .fillMaxWidth()
          .height(130.dp)
          .background(Color.LightGray)
      )
    }
  }
}