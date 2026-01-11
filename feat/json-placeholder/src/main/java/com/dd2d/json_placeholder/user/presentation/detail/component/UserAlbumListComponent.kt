package com.dd2d.json_placeholder.user.presentation.detail.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.dd2d.json_placeholder.album.domain.model.Album

@Composable
internal fun UserAlbumListComponent(
  albumList: List<Album>,
  onAlbumClick: (id: Int) -> Unit,
  modifier: Modifier = Modifier
) {
  LazyColumn(modifier = modifier) {
    items(items = albumList, key = Album::id) { item ->
      Text(
        text = item.title,
        fontWeight = FontWeight.W400,
        color = MaterialTheme.colorScheme.onSurface,
        fontSize = 14.sp,
        lineHeight = 1.2.em,
        modifier = Modifier
          .animateItem()
          .fillMaxWidth()
          .clickable { onAlbumClick(item.id) }
          .padding(vertical = 8.dp)
      )
    }
  }
}