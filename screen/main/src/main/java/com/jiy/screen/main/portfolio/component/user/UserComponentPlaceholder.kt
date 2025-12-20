package com.jiy.screen.main.portfolio.component.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jiy.ui.modifier.shimmer
import com.jiy.ui.theme.Shape

@Composable
internal fun UserComponentPlaceholder(
  modifier: Modifier = Modifier
) {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = modifier
  ) {
    // 프로필 이미지
    Box(
      modifier = Modifier
        .shimmer()
        .clip(CircleShape)
        .fillMaxWidth(0.28F)
        .aspectRatio(1F)
        .background(Color.LightGray)
    )
    // 이름
    Box(
      modifier = Modifier
        .padding(top = 12.dp)
        .shimmer()
        .width(80.dp)
        .height(25.dp)
        .background(Color.LightGray)
    )
    // 생년 월일
    Box(
      modifier = Modifier
        .padding(top = 12.dp)
        .shimmer()
        .width(120.dp)
        .height(25.dp)
        .background(Color.LightGray)
    )
    // 연락처, 깃헙 드
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(48.dp, Alignment.CenterHorizontally),
      modifier = Modifier
        .padding(top = 24.dp)
        .fillMaxWidth()
    ) {
      repeat(3) {
        Box(
          modifier = Modifier
            .clip(CircleShape)
            .shimmer()
            .size(48.dp)
            .background(Color.LightGray)
        )
      }
    }
    // 자기소개
    Box(
      modifier = Modifier
        .padding(top = 24.dp)
        .shimmer()
        .fillMaxWidth()
        .height(100.dp)
        .background(Color.LightGray, Shape.Card)
    )
  }
}