package com.jiy.screen.main.portfolio.component.career

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun CareersLabel(
  modifier: Modifier = Modifier
) {
  Text(
    text = "경력",
    color = MaterialTheme.colorScheme.onBackground,
    style = MaterialTheme.typography.titleMedium,
    modifier = modifier
  )
}