package com.dd2d.json_placeholder._core.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import com.dd2d.json_placeholder.post.domain.model.PostAuthor

@Composable
internal fun PostAuthorComponent(
  author: PostAuthor,
  modifier: Modifier = Modifier,
) {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(8.dp),
    modifier = modifier
      .widthIn(max = 150.dp)
      .clip(RoundedCornerShape(8.dp))
  ) {
    AsyncImage(
      model = ImageRequest.Builder(LocalContext.current)
        .data(author.profileImageUrl?: R.drawable.default_profile)
        .size(150)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .apply {
          if(author.profileImageUrl != null) {
            memoryCacheKey("${author.profileImageUrl}&size=20")
          }
        }
        .diskCachePolicy(CachePolicy.DISABLED)
        .build(),
      contentDescription = null,
      contentScale = ContentScale.Crop,
      modifier = Modifier
        .clip(CircleShape)
        .size(20.dp)
    )
    Text(
      text = author.nickname,
      fontWeight = FontWeight.W400,
      color = MaterialTheme.colorScheme.onSurface,
      fontSize = 14.sp,
      lineHeight = 1.2.em,
      maxLines = 1
    )
  }
}