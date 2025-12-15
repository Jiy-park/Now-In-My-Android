package com.jiy.screen.main.component.skill

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.jiy.ui.theme.BorderColor
import com.jiy.ui.theme.Padding
import com.jiy.ui.theme.PrimaryColor
import com.jiy.ui.theme.PrimaryContainerColor
import com.jiy.ui.theme.Shape
import com.jiy.ui.theme.SurfaceColor
import com.jiy.user.domain.model.Skill
import com.jiy.user.domain.model.SkillLevel

@Composable
internal fun SkillStackComponent(
  skillStack: List<Skill>,
  modifier: Modifier = Modifier
) {
  FlowRow(
    horizontalArrangement = Arrangement.spacedBy(6.dp),
    verticalArrangement = Arrangement.spacedBy(6.dp),
    modifier = modifier
      .padding(top = 8.dp)
      .fillMaxWidth()
  ) {
    skillStack.forEach { skill ->
      SkillChip(skill)
    }
  }
}

@Composable
private fun SkillChip(
  skill: Skill,
  modifier: Modifier = Modifier
) {
  val backgroundColor =
    if(skill.level >= SkillLevel.HIGH) PrimaryContainerColor
    else SurfaceColor

  val fontColor =
    if(skill.level >= SkillLevel.HIGH) PrimaryColor
    else MaterialTheme.colorScheme.onBackground

  val borderColor =
    if(skill.level >= SkillLevel.HIGH) PrimaryColor
    else BorderColor

  Row(
    modifier = modifier
      .clip(Shape.Chip)
      .background(backgroundColor)
      .border(width = 1.dp, color = borderColor, shape = Shape.Chip)
      .padding(Padding.Chip)
  ) {
    skill.iconUrl?.let { icon ->
      AsyncImage(
        model = icon,
        contentDescription = skill.name,
        modifier = Modifier.size(24.dp)
      )
      Spacer(Modifier.width(6.dp))
    }
    Text(
      text = skill.name,
      color = fontColor,
      style = MaterialTheme.typography.labelMedium,
    )
  }
}