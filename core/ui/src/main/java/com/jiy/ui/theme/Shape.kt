package com.jiy.ui.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

/**
 * 앱 내에서 공용으로 사용하는 모양 모음
 *
 */
object Shape {
  /** 모달 바텀 시트 모양 */
  val BottomSheet: Shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)

  /** 카드 컴포넌트 모양 */
  val Card: Shape = RoundedCornerShape(8.dp)

  /** 칩 컴포넌트 모양 */
  val Chip: Shape = CircleShape
}