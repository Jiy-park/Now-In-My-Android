package com.jiy.screen.main.portfolio.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jiy.ui.BuildConfig
import com.jiy.ui.component.TopBar

@Composable
internal fun MainScreenTopBar(
  modifier: Modifier = Modifier
) {
  TopBar(
    modifier = modifier,
    actions = {
      Text("v${BuildConfig.VERSION_NAME}(${BuildConfig.BUILD_TYPE})")
    },
  )
}