package com.dd2d.json_placeholder.album.presentation.detail.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ListItem
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
import com.dd2d.json_placeholder.album.domain.model.AlbumPhoto

@Composable
internal fun AlbumPhotoListComponent(
  albumPhotoList: List<AlbumPhoto>,
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current
  LazyColumn(modifier = modifier) {
    items(items = albumPhotoList, key = AlbumPhoto::id) { item ->
      ListItem(
        leadingContent = {
          AsyncImage(
            model = ImageRequest.Builder(context)
              .data(item.thumbnailUrl)
              .size(100)
              .memoryCachePolicy(CachePolicy.ENABLED)
              .memoryCacheKey("${item.url}?size=100")
              .diskCachePolicy(CachePolicy.DISABLED)
              .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
              .size(64.dp)
              .clip(RoundedCornerShape(8.dp))
          )
        },
        headlineContent = {
          Text(
            text = item.title,
            fontWeight = FontWeight.W400,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 14.sp,
            lineHeight = 1.2.em,
          )
        },
        modifier = Modifier
          .animateItem()
          .fillMaxWidth()
      )
    }
  }
}