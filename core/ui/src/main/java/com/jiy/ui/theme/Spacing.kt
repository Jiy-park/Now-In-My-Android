package com.jiy.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jiy.ui.theme.Spacing.Small

/** 앱 내에서 공용으로 사용하는 컴포넌트 간, 컴포넌트 내 간격 값 모음 */
object Spacing {
  /**
   * 대형 컴포넌트 간의 간격,
   * 예를 들어 스킬 스택과 경력같이 컴포넌트의 맥락이 변하는 경우에 사용합니다.
   * */
  val Large: Dp = 24.dp

  /**
   * 중형 컴포넌틑 간의 간격
   * 예를 들어 목록 내 아이템 간격 등에 사용합니다.
   */
  val Medium: Dp = 16.dp

  /**
   * 소형 컴포넌트 간의 간격
   * 예를 들어 유저의 이름, 이메일, 생년월일 등이 하나의 중형 컴포넌트에서 표시될때 주로 사용합니다.
   */
  val Small: Dp = 8.dp

  /**
   * 소형 컴포넌트 간의 간격 두번째. [Small]에 비해 더 작은 간격을 제공합니다.
   * 예를 들어 유저의 이름, 이메일, 생년월일 등이 하나의 중형 컴포넌트에서 표시될때 주로 사용합니다.
   */
  val ExtraSmall: Dp = 4.dp
}