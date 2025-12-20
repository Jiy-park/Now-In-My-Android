package com.joy.toy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.jiy.ui.modifier.scalableClick
import com.jiy.ui.theme.Padding
import com.jiy.ui.theme.Shape
import com.jiy.ui.theme.Spacing
import com.joy.toy.domain.toy_core.model.ToyListItem

@Composable
fun ToyListItemComponent(
  item: ToyListItem,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current
  Column(
    modifier = modifier
      .scalableClick(onClick = onClick, shape = Shape.Card)
      .clip(Shape.Card)
      .background(MaterialTheme.colorScheme.surface)
      .padding(Padding.Card)
  ) {
    Row(
      horizontalArrangement = Arrangement.spacedBy(Spacing.Medium),
      modifier = Modifier.fillMaxWidth()
    ) {
      AsyncImage(
        model = ImageRequest.Builder(context)
          .data(item.thumbnailUrl)
          .size(150)
          .build(),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
          .size(68.dp)
          .clip(Shape.Card)
          .background(Color.LightGray)
      )
      Column {
        Row(
          horizontalArrangement = Arrangement.spacedBy(Spacing.ExtraSmall),
          modifier = Modifier.fillMaxWidth()
        ) {
          Text(
            text = item.name,
            style = MaterialTheme.typography.titleSmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1F)
          )
          Text(
            text = item.version,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
              .background(MaterialTheme.colorScheme.secondaryContainer, shape = Shape.SmallTag)
              .padding(horizontal = 4.dp)
          )
        }
        Text(
          text = item.description,
          style = MaterialTheme.typography.bodySmall,
          maxLines = 3
        )
      }
    }
    FlowRow(
      horizontalArrangement = Arrangement.spacedBy(Spacing.ExtraSmall),
      maxLines = 1,
      modifier = Modifier
        .padding(top = Padding.Component.Vertical.Medium)
        .fillMaxWidth()
    ) {
      item.keywords.forEach { tag ->
        Text(
          text = "# $tag",
          style = MaterialTheme.typography.bodySmall,
          modifier = Modifier
            .background(MaterialTheme.colorScheme.surfaceVariant, shape = Shape.Chip)
            .padding(Padding.Tag)
        )
      }
    }
  }
}