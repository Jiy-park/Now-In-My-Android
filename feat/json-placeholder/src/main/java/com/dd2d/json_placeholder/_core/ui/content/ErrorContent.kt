package com.dd2d.json_placeholder._core.ui.content

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.dd2d.json_placeholder.R

@Composable
internal fun ErrorContent(
  message: String,
  throwable: Throwable,
  modifier: Modifier = Modifier
) {
  var expand by remember { mutableStateOf(false) }
  Box(modifier = modifier) {
    Container(
      onClick = { expand = !expand },
      modifier = Modifier
        .align(Alignment.Center)
        .fillMaxWidth()
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        Text(
          text = message,
          fontWeight = FontWeight.W500,
          color = MaterialTheme.colorScheme.onSurface,
          fontSize = 18.sp,
          lineHeight = 1.4.em,
          modifier = Modifier.weight(1F)
        )
        Icon(
          imageVector = ImageVector.vectorResource(
            id = if(expand) R.drawable.up else R.drawable.down
          ),
          contentDescription = if(expand) "오류 접기" else "오류 펼치기",
          tint = MaterialTheme.colorScheme.onSurface,
        )
      }

      AnimatedVisibility(visible = expand) {
        Text(
          text = throwable.localizedMessage?: "알 수 없는 오류",
          fontWeight = FontWeight.W400,
          color = MaterialTheme.colorScheme.onSurface,
          fontSize = 14.sp,
          lineHeight = 1.2.em,
        )
      }
    }
  }
}

@Composable
private fun Container(
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  content: @Composable ColumnScope.() -> Unit
) {
  Column(
    content = content,
    modifier = modifier
      .clip(RoundedCornerShape(16.dp))
      .background(color = MaterialTheme.colorScheme.surfaceContainer)
      .clickable(onClick = onClick)
      .padding(horizontal = 20.dp, vertical = 16.dp)
  )
}