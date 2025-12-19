package com.dd2d.now_in_my_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.dd2d.now_in_my_android.navigation.AppNavHost
import com.dd2d.now_in_my_android.ui.theme.NowInMyAndroidTheme
import com.jiy.screen.main.home.MainScreenRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      NowInMyAndroidTheme {
        AppNavHost(
          startDestination = MainScreenRoute,
          modifier = Modifier.fillMaxSize(),
        )
      }
    }
  }
}