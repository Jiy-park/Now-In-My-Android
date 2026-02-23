package com.dd2d.maps.google_map.presentation.map.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.dd2d.maps.R

@Composable
internal fun GoogleMapScreenTopBar(
  onBack: () -> Unit,
  searchTextState: TextFieldState,
  isImeVisible: Boolean,
  isSearchMode: Boolean,
  onSearchModeToggleRequest: () -> Unit,
  modifier: Modifier = Modifier
) {
  val animatedBackIconRotateZ by animateFloatAsState(if(isImeVisible) -90F else 0F)

  Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween,
    modifier = modifier
  ) {
    UtilButton(
      onClick = onBack,
      imageVector = ImageVector.vectorResource(R.drawable.left),
      contentDescription = "뒤로가기",
      modifier = Modifier.graphicsLayer { rotationZ = animatedBackIconRotateZ }
    )
    Row(
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier
        .clip(CircleShape)
        .background(MaterialTheme.colorScheme.surface)
        .border(width = 1.dp, color = MaterialTheme.colorScheme.outlineVariant, shape = CircleShape)
        .clickable(enabled = !isSearchMode, onClick = onSearchModeToggleRequest)
        .padding(all = 8.dp)
    ) {
      Icon(
        imageVector = ImageVector.vectorResource(R.drawable.search),
        contentDescription = null,
      )
      AnimatedVisibility(visible = isSearchMode) {
        SearchField(state = searchTextState)
      }
    }
  }
}

@Composable
private fun UtilButton(
  onClick: () -> Unit,
  imageVector: ImageVector,
  contentDescription: String?,
  modifier: Modifier = Modifier
) {
  OutlinedIconButton(
    onClick = onClick,
    colors = IconButtonDefaults.outlinedIconButtonColors(
      containerColor = MaterialTheme.colorScheme.surface
    ),
    shape = CircleShape,
    border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outlineVariant),
    modifier = modifier
  ) {
    Icon(
      imageVector = imageVector,
      contentDescription = contentDescription,
    )
  }
}

@Composable
private fun SearchField(
  state: TextFieldState,
  modifier: Modifier = Modifier
) {
  val textStyle = TextStyle.Default.copy(
    fontWeight = FontWeight.Normal,
    color = MaterialTheme.colorScheme.onSurface,
    fontSize = 14.sp,
    lineHeight = 1.4.em,
  )
  BasicTextField(
    state = state,
    lineLimits = TextFieldLineLimits.SingleLine,
    textStyle = textStyle,
    cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
    decorator = { innerTextField ->
      Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
          .fillMaxWidth()
      ) {
        Box(modifier = Modifier.weight(1F)) {
          if(state.text.isEmpty()) {
            Text(
              text = "장소 검색하기",
              style = textStyle.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
            )
          }
          innerTextField()
        }
        if(state.text.isNotEmpty()) {
          Icon(
            imageVector = ImageVector.vectorResource(R.drawable.clear_circle),
            contentDescription = null,
            modifier = Modifier
              .clip(CircleShape)
              .clickable(onClick = state::clearText)
          )
        }
      }
    },
    modifier = modifier
  )
}