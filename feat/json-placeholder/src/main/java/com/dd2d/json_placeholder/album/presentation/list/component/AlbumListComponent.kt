package com.dd2d.json_placeholder.album.presentation.list.component

import androidx.compose.foundation.clickable
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.dd2d.json_placeholder._core.ui.component.AuthorComponent
import com.dd2d.json_placeholder.album.domain.model.Album

@Composable
internal fun AlbumListComponent(
  album: Album,
  onClick: (id: Int) -> Unit,
  modifier: Modifier = Modifier
) {
  ListItem(
    overlineContent = {
      AuthorComponent(
        authorNickname = album.author.nickname,
        authorProfileImageUrl = album.author.profileImageUrl,
      )
    },
    headlineContent = {
      Text(
        text = album.title,
        fontWeight = FontWeight.W600,
        color = MaterialTheme.colorScheme.onSurface,
        fontSize = 18.sp,
        lineHeight = 1.4.em,
        maxLines = 1
      )
    },
    modifier = modifier
      .clickable { onClick(album.id) }
  )
}