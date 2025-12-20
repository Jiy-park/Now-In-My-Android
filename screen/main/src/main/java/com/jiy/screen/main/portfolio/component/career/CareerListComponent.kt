package com.jiy.screen.main.portfolio.component.career

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.jiy.career.domain.model.CareerListItem
import com.jiy.career.presentation.component.CareerListItemComponent
import com.jiy.ui.R
import com.jiy.ui.modifier.scalableClick
import com.jiy.ui.theme.BorderColor
import com.jiy.ui.theme.PrimaryColor
import com.jiy.ui.theme.SurfaceColor

@Composable
internal fun CareerListComponent(
  careers: List<CareerListItem>,
  onClick: (id: Int) -> Unit,
  onMoreClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    verticalArrangement = Arrangement.spacedBy(8.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = modifier
      .padding(top = 8.dp)
      .fillMaxWidth()
  ) {
    careers.take(2).forEach { career ->
      CareerListItemComponent(
        career = career,
        onClick = { onClick(career.id) },
        modifier = Modifier
          .fillMaxWidth()
      )
    }

    if(careers.size > 2) {
      MoreCareerButton(
        onClick = onMoreClick,
        modifier = Modifier
          .padding(top = 8.dp)
          .fillMaxWidth()
      )
    }
  }
}

@Composable
private fun MoreCareerButton(
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
    modifier = modifier
      .clip(CircleShape)
      .scalableClick(onClick = onClick)
      .background(SurfaceColor)
      .border(width = 1.dp, color = BorderColor, shape = CircleShape)
      .padding(vertical = 8.dp)
  ) {
    Text(
      text = "경력 더보기",
      color = MaterialTheme.colorScheme.onBackground,
      style = MaterialTheme.typography.labelLarge,
    )
    Icon(
      imageVector = ImageVector.vectorResource(R.drawable.right),
      contentDescription = "경력 더보기",
      tint = PrimaryColor
    )
  }
}