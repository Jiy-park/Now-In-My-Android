package com.jiy.screen.career.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jiy.career.domain.model.CareerSkill
import com.jiy.ui.theme.Padding
import com.jiy.ui.theme.Shape
import com.jiy.ui.theme.Spacing

@Composable
internal fun CareerSkillStack(
  skills: List<CareerSkill>,
  modifier: Modifier = Modifier
) {
  FlowRow(
    verticalArrangement = Arrangement.spacedBy(Spacing.Small),
    horizontalArrangement = Arrangement.spacedBy(Spacing.Small),
    modifier = modifier
  ) {
    skills.forEach { skill ->
      Text(
        text = skill.name,
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier
          .background(MaterialTheme.colorScheme.surface, Shape.Chip)
          .padding(Padding.Chip)
      )
    }
  }
}