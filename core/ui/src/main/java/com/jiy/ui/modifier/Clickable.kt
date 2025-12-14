package com.jiy.ui.modifier

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.material3.ripple
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.Role

/**
 * 스케일 애니메이션이 적용된 [Modifier.clickable]
 *
 * @param interactionSource 클릭 이벤트에 대한 `source`
 * @param ripple 클릭에 대한 `indicator`
 * @param shape [ripple]의 모양을 결정하는 [Shape]
 * @param enabled 클릭 가능 여부
 * @param onClickLabel [onClick]의 의미론적 / 접근성 라벨
 * @param role 사용자 인터페이스 요소의 유형
 * @param initialScale 클릭전 `scale`
 * @param pressedScale 클릭후 `scale`
 * @param onClick 클릭 이벤트
 */
fun Modifier.scalableClick(
  interactionSource: MutableInteractionSource? = null,
  ripple: Indication? = ripple(),
  shape: Shape = RectangleShape,
  enabled: Boolean = true,
  onClickLabel: String? = null,
  role: Role? = null,
  initialScale: Float = 1F,
  pressedScale: Float = 0.98F,
  onClick: () -> Unit,
): Modifier = composed {
  val source = interactionSource?: remember { MutableInteractionSource() }
  val scale = remember { Animatable(initialScale) }

  LaunchedEffect(source) {
    source.interactions.collect { interaction ->
      when(interaction) {
        is PressInteraction.Press -> scale.animateTo(pressedScale, tween(120))
        is PressInteraction.Release,
        is PressInteraction.Cancel -> scale.animateTo(1f, tween(120))
      }
    }
  }

  this
    .graphicsLayer {
      this.scaleX = scale.value
      this.scaleY = scale.value
      this.shape = shape
      this.clip = true
    }
    .clickable(
      interactionSource = source,
      indication = ripple,
      enabled = enabled,
      onClickLabel = onClickLabel,
      role = role,
      onClick = onClick,
    )
}