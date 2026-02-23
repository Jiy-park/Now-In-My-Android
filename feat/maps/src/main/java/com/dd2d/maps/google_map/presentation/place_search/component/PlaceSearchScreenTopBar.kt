package com.dd2d.maps.google_map.presentation.place_search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.dd2d.maps.R

@Composable
internal fun PlaceSearchScreenTopBar(
  state: TextFieldState,
  onBackClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  val style = TextStyle.Default.copy(
    fontWeight = FontWeight.Normal,
    color = MaterialTheme.colorScheme.onSurface,
    fontSize = 14.sp,
    lineHeight = 1.4.em,
  )
  Box(modifier = modifier) {
    BasicTextField(
      state = state,
      lineLimits = TextFieldLineLimits.SingleLine,
      cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
      textStyle = style,
      decorator = { innerTextField ->
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier
            .fillMaxWidth()
            .clip(CircleShape)
            .background(Color.White)
            .border(width = 1.dp, color = MaterialTheme.colorScheme.outlineVariant, shape = CircleShape)
            .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
          IconButton(
            onClick = onBackClick,
            modifier = Modifier.size(36.dp)
          ) {
            Icon(
              imageVector = ImageVector.vectorResource(R.drawable.left),
              contentDescription = "뒤로가기"
            )
          }
          Box(modifier = Modifier.weight(1F)) {
            if(state.text.isBlank()) {
              Text(
                text = "검색어를 입력해주세요.",
                style = style.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
              )
            }
            innerTextField()
          }
          if(state.text.isNotBlank()) {
            IconButton(
              onClick = state::clearText,
              modifier = Modifier.size(36.dp)
            ) {
              Icon(
                imageVector = ImageVector.vectorResource(R.drawable.clear_circle),
                contentDescription = "검색어 모두 지우기"
              )
            }
          }
        }
      },
      modifier = Modifier.fillMaxWidth()
    )
  }
}