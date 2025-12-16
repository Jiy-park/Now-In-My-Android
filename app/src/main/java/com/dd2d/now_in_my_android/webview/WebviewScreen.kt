package com.dd2d.now_in_my_android.webview

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.jiy.ui.component.TopBar

@Composable
fun WebviewScreen(
  onBack: () -> Unit,
  modifier: Modifier = Modifier,
  viewModel: WebviewViewModel = hiltViewModel()
) {
  BackHandler(onBack = onBack)
  Scaffold(
    topBar = { TopBar(title = viewModel.webViewScreenRoute.title, onBack = onBack) },
    modifier = modifier
  ) { inner ->
    WebViewContent(
      url = viewModel.webViewScreenRoute.url,
      webViewState = viewModel.webViewState,
      modifier = Modifier
        .consumeWindowInsets(inner)
        .fillMaxSize()
        .padding(inner)
    )
  }
}

@Composable
private fun WebViewContent(
  url: String,
  webViewState: Bundle,
  modifier: Modifier = Modifier
) {
  AndroidView(
    factory = { context ->
      WebView(context).apply {
        webViewClient = object: WebViewClient() {}
        webChromeClient = object: WebChromeClient() {}
        with(settings) {
          settings.javaScriptEnabled = true
          settings.domStorageEnabled = true
          settings.loadWithOverviewMode = true
          settings.useWideViewPort = true
          settings.setSupportZoom(false)
          settings.builtInZoomControls = false
          settings.displayZoomControls = false
          settings.cacheMode = WebSettings.LOAD_DEFAULT
          settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
      }
    },
    update = { webView ->
      with(webView) {
        when(webViewState.isEmpty) {
          true -> webView.loadUrl(url)
          false -> webView.restoreState(webViewState)
        }
      }
    },
    onReset = { webView ->
      webView.saveState(webViewState)
    },
    modifier = modifier
  )
}