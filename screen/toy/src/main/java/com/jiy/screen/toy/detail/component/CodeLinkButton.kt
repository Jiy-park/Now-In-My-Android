package com.jiy.screen.toy.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.jiy.ui.modifier.scalableClick
import com.jiy.ui.theme.Padding
import com.jiy.ui.theme.Shape
import com.jiy.ui.theme.Spacing

@Composable
internal fun CodeLinkButton(
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(Spacing.Small, Alignment.CenterHorizontally),
    modifier = modifier
      .scalableClick(onClick = onClick, shape = Shape.Card)
      .clip(Shape.Card)
      .background(color = MaterialTheme.colorScheme.primaryContainer)
      .padding(horizontal = Padding.M, vertical = Padding.S)
  ) {
    Icon(
      imageVector = ImageVector.vectorResource(com.jiy.ui.R.drawable.code),
      contentDescription = null,
      tint = Color.White
    )
    Text(
      text = "소스 코드 보기",
      color = Color.White,
      style = MaterialTheme.typography.bodyMedium
    )
  }
}