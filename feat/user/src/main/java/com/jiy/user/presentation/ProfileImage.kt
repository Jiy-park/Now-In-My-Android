package com.jiy.user.presentation

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage

@Composable
fun ProfileImage(
  image: Any?,
  modifier: Modifier = Modifier,
  contentScale: ContentScale = ContentScale.Crop
) {

  AsyncImage(
    model = image,
    contentDescription = "프로필 이미지",
    contentScale = contentScale,
    modifier = modifier
      .clip(CircleShape)
      .aspectRatio(1F)
  )
}