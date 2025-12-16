package com.jiy.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.jiy.ui.theme.Spacing

@Composable
fun SectionLabel(
  label: String,
  modifier: Modifier = Modifier,
  @DrawableRes vectorIconRes: Int? = null,
) {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(Spacing.Small),
    modifier = modifier
  ) {
    vectorIconRes?.let { res ->
      Icon(
        imageVector = ImageVector.vectorResource(vectorIconRes),
        tint = MaterialTheme.colorScheme.primary,
        contentDescription = label
      )

    }
    Text(
      text = label,
      color = MaterialTheme.colorScheme.onBackground,
      style = MaterialTheme.typography.titleMedium,
    )
  }
}