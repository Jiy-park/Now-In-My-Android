package com.dd2d.json_placeholder.user.presentation.detail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import com.dd2d.json_placeholder.R
import com.dd2d.json_placeholder.user.domain.model.User

@Composable
internal fun UserProfile(
  user: User,
  modifier: Modifier = Modifier
) {
  Column(modifier = modifier) {
    AsyncImage(
      model = ImageRequest.Builder(LocalContext.current)
        .data(user.profileImageUrl?: R.drawable.default_profile)
        .diskCachePolicy(CachePolicy.DISABLED)
        .memoryCachePolicy(CachePolicy.DISABLED)
        .size(200)
        .build(),
      contentDescription = null,
      contentScale = ContentScale.Crop,
      modifier = Modifier
        .clip(CircleShape)
        .size(64.dp)
    )
    Text(
      text = user.nickname,
      fontWeight = FontWeight.W500,
      color = MaterialTheme.colorScheme.onSurface,
      fontSize = 18.sp,
      lineHeight = 1.4.em,
      modifier = Modifier.padding(16.dp)
    )
  }
}