package com.dd2d.now_in_my_android.webview

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jiy.ui.navigation.ScreenRoute
import kotlinx.serialization.Serializable

@Serializable
data class WebviewScreenRoute(val title: String?, val url: String) : ScreenRoute

fun NavGraphBuilder.routeWebviewScreen(
  onBack: () -> Unit,
  modifier: Modifier = Modifier,
) {
  composable<WebviewScreenRoute> {
    WebviewScreen(
      onBack = onBack,
      modifier = modifier,
    )
  }
}