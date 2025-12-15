package com.jiy.career.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.jiy.career.domain.model.CareerListItem
import com.jiy.ui.modifier.scalableClick
import com.jiy.ui.theme.BorderColor
import com.jiy.ui.theme.Shape
import com.jiy.ui.theme.Spacing
import com.jiy.ui.theme.SurfaceColor
import com.jiy.ui.util.format

@Composable
fun CareerListItemComponent(
  career: CareerListItem,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Row(
    horizontalArrangement = Arrangement.spacedBy(Spacing.Medium),
    modifier = modifier
      .clip(Shape.Card)
      .scalableClick(onClick = onClick, shape = Shape.Card)
      .fillMaxWidth()
      .background(SurfaceColor)
      .border(width = 1.dp, color = BorderColor, shape = Shape.Card)
      .padding(Spacing.Medium)
  ) {
    AsyncImage(
      model = career.companyImageUrl?: com.jiy.career.R.drawable.office ,
      contentDescription = career.companyName,
      contentScale = ContentScale.Crop,
      modifier = Modifier
        .clip(Shape.Card)
        .size(64.dp)
    )
    Column(
      modifier = Modifier
        .fillMaxWidth()
    ) {
      Text(
        text = career.companyName,
        color = MaterialTheme.colorScheme.onBackground,
        style = MaterialTheme.typography.titleSmall,
      )
      Text(
        text = career.position,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        style = MaterialTheme.typography.bodyMedium,
      )
      Text(
        text = "${career.startDate.format("yyyy.MM.dd")} ~ ${career.endDate?.format("yyyy.MM.dd")?: "재직중"}",
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        style = MaterialTheme.typography.bodyMedium,
      )
    }
  }
}