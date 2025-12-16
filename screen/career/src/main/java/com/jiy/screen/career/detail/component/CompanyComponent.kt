package com.jiy.screen.career.detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jiy.career.presentation.component.CompanyImage
import com.jiy.ui.theme.Padding
import com.jiy.ui.theme.Spacing
import com.jiy.ui.util.format
import java.time.LocalDate

@Composable
internal fun CompanyComponent(
  companyName: String,
  companyImageUrl: String?,
  position: String,
  startDate: LocalDate,
  endDate: LocalDate?,
  modifier: Modifier = Modifier
) {
  Row(
    horizontalArrangement = Arrangement.spacedBy(Padding.Screen.Horizontal),
    modifier = modifier
      .fillMaxWidth()
  ) {
    CompanyImage(
      companyName = companyName,
      image = companyImageUrl,
      modifier = Modifier
        .heightIn(max = 64.dp)
        .fillMaxHeight()
    )
    Column(
      verticalArrangement = Arrangement.spacedBy(Spacing.ExtraSmall),
      modifier = Modifier.fillMaxWidth()
    ) {
      Text(text = companyName, style = MaterialTheme.typography.titleSmall)
      Text(text = position, style = MaterialTheme.typography.bodyMedium)
      Text(text = "${startDate.format("yyyy.MM.dd")} ~ ${endDate?.format("yyyy.MM.dd")?: "현재 (재직중)"}", style = MaterialTheme.typography.bodyMedium)
    }
  }
}