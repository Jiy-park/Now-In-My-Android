package com.dd2d.maps.google_map.presentation.map.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

@Composable
internal fun GoogleMapScreenTopBar(
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Row(
    horizontalArrangement = Arrangement.spacedBy(8.dp),
    verticalAlignment = Alignment.CenterVertically,
    modifier = modifier
      .fillMaxWidth()
      .clip(CircleShape)
      .background(Color.White)
      .border(width = 1.dp, color = MaterialTheme.colorScheme.outlineVariant, shape = CircleShape)
      .clickable(onClick = onClick)
      .padding(all = 12.dp)
  ) {
    Icon(
      imageVector = ImageVector.vectorResource(com.dd2d.maps.R.drawable.search),
      contentDescription = null
    )
    Text(
      text = "장소 검색하기",
      fontWeight = FontWeight.Normal,
      color = MaterialTheme.colorScheme.onSurfaceVariant,
      fontSize = 14.sp,
      lineHeight = 1.4.em,
    )
  }
}