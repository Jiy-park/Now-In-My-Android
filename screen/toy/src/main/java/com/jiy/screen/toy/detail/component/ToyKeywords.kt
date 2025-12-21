package com.jiy.screen.toy.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jiy.ui.theme.Padding
import com.jiy.ui.theme.Shape
import com.jiy.ui.theme.Spacing

@Composable
internal fun ToyKeywords(
  keywords: List<String>,
  modifier: Modifier = Modifier
) {
  FlowRow(
    horizontalArrangement = Arrangement.spacedBy(Spacing.Small),
    verticalArrangement = Arrangement.spacedBy(Spacing.Small),
    modifier = modifier
  ) {
    keywords.forEach { keyword ->
      Text(
        text = keyword,
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier
          .background(color = MaterialTheme.colorScheme.surfaceVariant, shape = Shape.Chip)
          .padding(Padding.Chip)
      )
    }
  }
}