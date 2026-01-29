package com.dd2d.maps.google_map.presentation.map.content

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

@Composable
internal fun ErrorContent(
  throwable: Throwable,
  onPermissionGranted: () -> Unit,
  modifier: Modifier = Modifier
) {
  Box(
    contentAlignment = Alignment.Center,
    modifier = modifier
  ) {
    if(throwable is SecurityException) {
      val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
          if(isGranted) {
            onPermissionGranted()
          }
        }
      )
      TextButton(
        onClick = { locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION) }
      ) {
        Text(
          text = "위치 권한을 허용해 주세요.",
          fontWeight = FontWeight.W500,
          color = MaterialTheme.colorScheme.error,
          fontSize = 14.sp,
          lineHeight = 1.4.em
        )
      }
    }
    else {
      Text(text = throwable.localizedMessage?: "오류")
    }
  }
}