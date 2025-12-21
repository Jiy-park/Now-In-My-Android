package com.jiy.screen.toy.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.jiy.screen.toy.R
import com.jiy.ui.modifier.scalableClick
import com.jiy.ui.theme.Padding
import com.jiy.ui.theme.Shape
import com.jiy.ui.theme.Spacing
import com.joy.toy.domain.toy_core.model.ToyVersion

@Composable
internal fun VersionComponent(
  version: ToyVersion,
  onMoreVersionClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween,
    modifier = modifier
  ) {
    VersionTag(version = version.version)
    MoreVersionText(onClick = onMoreVersionClick)
  }
}

@Composable
private fun VersionTag(
  version: String,
  modifier: Modifier = Modifier,
  containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
  contentColor: Color = MaterialTheme.colorScheme.primary,
) {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(Spacing.ExtraSmall),
    modifier = modifier
      .background(color = containerColor, shape = Shape.Chip)
      .border(width = 1.dp, color = contentColor, shape = Shape.Chip)
      .padding(Padding.Chip)
  ) {
    Icon(
      imageVector = ImageVector.vectorResource(R.drawable.version),
      contentDescription = "version",
      tint = MaterialTheme.colorScheme.primary,
      modifier = Modifier.size(16.dp)
    )
    Text(
      text = version,
      color = MaterialTheme.colorScheme.primary,
      style = MaterialTheme.typography.bodySmall
    )
  }
}

@Composable
private fun MoreVersionText(
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  text: String = "릴리즈 노트 모두 보기",
  color: Color = MaterialTheme.colorScheme.primaryContainer,
) {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(Spacing.ExtraSmall),
    modifier = modifier
      .scalableClick(onClick = onClick, shape = Shape.SmallTag)
      .padding(Padding.Tag)
  ) {
    Text(
      text = text,
      color = color,
      style = MaterialTheme.typography.bodySmall
    )
    Icon(
      imageVector = ImageVector.vectorResource(com.jiy.ui.R.drawable.right),
      contentDescription = text,
      tint = color,
      modifier = Modifier.size(16.dp)
    )
  }
}