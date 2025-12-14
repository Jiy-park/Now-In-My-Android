package com.jiy.screen.main.component.skill

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jiy.ui.modifier.shimmer
import com.jiy.ui.theme.ChipPaddingValues
import com.jiy.ui.theme.ChipShape

@Composable
internal fun SkillStackComponentPlaceholder(
  modifier: Modifier = Modifier
) {
  FlowRow(
    horizontalArrangement = Arrangement.spacedBy(6.dp),
    verticalArrangement = Arrangement.spacedBy(6.dp),
    modifier = modifier
  ) {
    repeat(5) {
      Box(
        modifier = modifier
          .clip(ChipShape)
          .shimmer()
          .width((50..150).random().dp)
          .height(30.dp)
          .background(Color.LightGray)
          .padding(ChipPaddingValues)
      )
    }
  }
}