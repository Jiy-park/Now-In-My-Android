package com.jiy.ui.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jiy.ui.theme.Padding.Screen.Horizontal
import com.jiy.ui.theme.Padding.Screen.Vertical

/**
 * 앱 내에서 공용으로 사용하는 패딩 값 모음
 * */
object Padding {
  /** 화면 단위 패딩 값 */
  object Screen {
    /** 좌우 패딩 */
    val Horizontal: Dp = 16.dp

    /** 상하 패딩 */
    val Vertical: Dp = 24.dp

    /** 상하좌우 패딩
     *
     * @see Horizontal
     * @see Vertical
     * */
    val Inset: PaddingValues = PaddingValues(horizontal = Horizontal, vertical = Vertical)
  }

  /** 컴포넌트 단위 패딩 값 */
  object Component {
    /** 좌우 패딩 */
    object Horizontal {
      val Large: Dp = 16.dp
      val Medium: Dp = 12.dp
      val Small: Dp = 8.dp
    }

    /** 상하 패딩 */
    object Vertical {
      val Large: Dp = 16.dp
      val Medium: Dp = 12.dp
      val Small: Dp = 8.dp
    }
  }

  val BottomSheet: PaddingValues = PaddingValues(horizontal = Component.Horizontal.Medium, vertical = Component.Vertical.Medium)
  val Card: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
  val Chip: PaddingValues = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
  val Tag: PaddingValues = PaddingValues(horizontal = 12.dp, vertical = 4.dp)
}