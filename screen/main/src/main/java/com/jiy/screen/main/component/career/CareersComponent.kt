package com.jiy.screen.main.component.career

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
import com.jiy.screen.main.R
import com.jiy.screen.main._core.ui.modifier.scalableClick
import com.jiy.screen.main.model.BorderColor
import com.jiy.screen.main.model.CardPaddingValues
import com.jiy.screen.main.model.CardShape
import com.jiy.screen.main.model.ChipPaddingValues
import com.jiy.screen.main.model.ChipShape
import com.jiy.screen.main.model.PrimaryColor
import com.jiy.screen.main.model.PrimaryContainerColor
import com.jiy.screen.main.model.SurfaceColor
import com.jiy.user.domain.model.Career

@Composable
internal fun CareersComponent(
  careers: List<Career>,
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
      CareerComponent(
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
private fun CareerComponent(
  career: Career,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier
      .clip(CardShape)
      .scalableClick(onClick = onClick, shape = CardShape)
      .fillMaxWidth()
      .background(SurfaceColor)
      .border(width = 1.dp, color = BorderColor, shape = CardShape)
      .padding(CardPaddingValues)
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier.fillMaxWidth()
    ) {
      Text(
        text = career.companyName,
        color = MaterialTheme.colorScheme.onBackground,
        style = MaterialTheme.typography.titleSmall,
        modifier = Modifier.weight(1F)
      )
      if(career.endDate == null) {
        Text(
          text = "재직중",
          color = PrimaryColor,
          style = MaterialTheme.typography.bodySmall,
          modifier = Modifier
            .background(PrimaryContainerColor, ChipShape)
            .padding(ChipPaddingValues)
        )
      }
    }
    Text(
      text = career.position,
      color = MaterialTheme.colorScheme.onSurfaceVariant,
      style = MaterialTheme.typography.bodyMedium,
    )
    Text(
      text = "${career.startDate} ~ ${career.endDate?: ""}",
      color = MaterialTheme.colorScheme.onSurfaceVariant,
      style = MaterialTheme.typography.bodyMedium,
    )
    Text(
      text = career.description,
      color = MaterialTheme.colorScheme.onBackground,
      style = MaterialTheme.typography.bodyLarge,
      maxLines = 3,
      modifier = Modifier
        .padding(top = 8.dp)
    )
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