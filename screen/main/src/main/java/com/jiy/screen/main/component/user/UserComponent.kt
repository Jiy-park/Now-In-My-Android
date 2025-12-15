package com.jiy.screen.main.component.user

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.jiy.ui.modifier.scalableClick
import com.jiy.ui.theme.BorderColor
import com.jiy.ui.theme.Padding
import com.jiy.ui.theme.PrimaryColor
import com.jiy.ui.theme.PrimaryContainerColor
import com.jiy.ui.theme.Shape
import com.jiy.ui.theme.SurfaceColor
import com.jiy.user.domain.model.User
import com.jiy.user.presentation.ProfileImage

@Composable
internal fun UserComponent(
  user: User,
  onPhoneIconClick: (phoneNum: String) -> Unit,
  onEmailIconClick: (email: String) -> Unit,
  onGithubIconClick: (url: String) -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = modifier
  ) {
    // 프로필
    ProfileImage(
      image = user.profileImageUrl,
      modifier = Modifier.fillMaxWidth(0.28F)
    )
    // 이름
    Text(
      text = user.name,
      color = MaterialTheme.colorScheme.onBackground,
      style = MaterialTheme.typography.headlineMedium,
      modifier = Modifier
        .padding(top = 12.dp)
    )
    // 생년월일
    Row(
      horizontalArrangement = Arrangement.spacedBy(4.dp),
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier
        .padding(top = 12.dp)
    ) {
      Icon(
        imageVector = ImageVector.vectorResource(R.drawable.birth),
        contentDescription = "생년월일 ${user.birth}",
        tint = MaterialTheme.colorScheme.onSurface
      )
      Text(
        text = user.birth,
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.titleSmall,
      )
    }
    // 연락처, 깃헙 등
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(48.dp, Alignment.CenterHorizontally),
      modifier = Modifier
        .padding(top = 24.dp)
        .fillMaxWidth()
    ) {
      CircleIconButton(
        label = "연락처",
        icon = ImageVector.vectorResource(R.drawable.phone),
        onClick = { onPhoneIconClick(user.phoneNum) },
      )
      CircleIconButton(
        label = "이메일",
        icon = ImageVector.vectorResource(R.drawable.email),
        onClick = { onEmailIconClick(user.email) },
      )
      CircleIconButton(
        label = "GitHub",
        icon = ImageVector.vectorResource(R.drawable.code),
        onClick = { onGithubIconClick(user.githubUrl) },
      )
    }
    // 자기 소개
    user.shortIntroduction?.let { introduction ->
      Text(
        text = "자기소개",
        color = MaterialTheme.colorScheme.onBackground,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier
          .align(Alignment.Start)
          .padding(top = 24.dp)
      )
      Column(
        modifier = Modifier
          .padding(top = 12.dp)
          .fillMaxWidth()
          .background(SurfaceColor, Shape.Card)
          .border(width = 1.dp, color = BorderColor, shape = Shape.Card)
          .padding(Padding.Card)
      ) {
        Text(
          text = introduction,
          color = MaterialTheme.colorScheme.onBackground,
          style = MaterialTheme.typography.bodyMedium,
        )
      }
    }
  }
}

@Composable
private fun CircleIconButton(
  label: String,
  icon: ImageVector,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    verticalArrangement = Arrangement.spacedBy(4.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = modifier
  ) {
    Box(
      contentAlignment = Alignment.Center,
      modifier = Modifier
        .clip(CircleShape)
        .scalableClick(onClick = onClick, pressedScale = 0.9F, shape = CircleShape)
        .size(48.dp)
        .background(PrimaryContainerColor)
    ) {
      Icon(
        imageVector = icon,
        contentDescription = label,
        tint = PrimaryColor,
      )
    }
    Text(
      text = label,
      color = MaterialTheme.colorScheme.onSurface,
      style = MaterialTheme.typography.labelMedium
    )
  }
}