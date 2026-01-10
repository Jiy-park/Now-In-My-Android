package com.dd2d.json_placeholder.user.presentation.detail.component

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import com.dd2d.json_placeholder.R
import com.dd2d.json_placeholder.user.domain.model.User

@Composable
internal fun UserProfileImage(
  user: User,
  modifier: Modifier = Modifier
) {
  AsyncImage(
    model = ImageRequest.Builder(LocalContext.current)
      .data(user.profileImageUrl?: R.drawable.default_profile)
      .diskCachePolicy(CachePolicy.DISABLED)
      .memoryCachePolicy(CachePolicy.DISABLED)
      .size(200)
      .build(),
    contentDescription = "${user.nickname} 프로필 이미지",
    contentScale = ContentScale.Crop,
    modifier = modifier
      .size(64.dp)
  )
}