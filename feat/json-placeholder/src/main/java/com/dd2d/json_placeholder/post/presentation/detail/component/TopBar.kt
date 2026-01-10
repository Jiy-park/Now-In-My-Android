package com.dd2d.json_placeholder.post.presentation.detail.component

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.dd2d.json_placeholder.R

@Composable
internal fun TopBar(
  title: String,
  onBack: () -> Unit,
  modifier: Modifier = Modifier
) {
  TopAppBar(
    title = {
      Text(text = title)
    },
    navigationIcon = {
      IconButton(
        onClick = onBack,
        content = {
          Icon(
            imageVector = ImageVector.vectorResource(R.drawable.left),
            contentDescription = "뒤로 가기"
          )
        }
      )
    },
    modifier = modifier
  )
}