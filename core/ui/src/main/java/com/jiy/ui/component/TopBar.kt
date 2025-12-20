package com.jiy.ui.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.jiy.ui.R

@Composable
fun TopBar(
  title: String? = null,
  onBack: (() -> Unit)? = null,
  actions: @Composable RowScope.() -> Unit = {},
  modifier: Modifier = Modifier,
) {
  CenterAlignedTopAppBar(
    title = {
      title?.let {
        Text(
          text = title,
          style = MaterialTheme.typography.titleLarge
        )
      }
    },
    modifier = modifier,
    navigationIcon = {
      onBack?.let {
        IconButton(onClick = onBack) {
          Icon(
            imageVector = ImageVector.vectorResource(R.drawable.left_arrow),
            contentDescription = "뒤로가기"
          )
        }
      }
    },
    actions = actions,
    colors = TopAppBarDefaults.topAppBarColors(),
  )
}