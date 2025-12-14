package com.jiy.screen.main.component.skill

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RichTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipAnchorPosition
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.jiy.screen.main.R
import com.jiy.ui.modifier.scalableClick
import com.jiy.ui.theme.CardPaddingValues
import com.jiy.ui.theme.CardShape
import com.jiy.ui.theme.ChipShape
import kotlinx.coroutines.launch

@Composable
internal fun SkillStackLabel(
  modifier: Modifier = Modifier
) {
  val scope = rememberCoroutineScope()
  val tooltipState = rememberTooltipState(isPersistent = true)
  Row(modifier = modifier) {
    Text(
      text = "기술 스택",
      color = MaterialTheme.colorScheme.onBackground,
      style = MaterialTheme.typography.titleMedium,
    )

    TooltipBox(
      positionProvider = TooltipDefaults.rememberTooltipPositionProvider(
        positioning = TooltipAnchorPosition.Above,
      ),
      state = tooltipState,
      tooltip = {
        RichTooltip(
          text = {
            Text("대충 기술 스택에는 세가지 단계가 있고, 제일 높은 단계만 하이라이팅 된다는뜻")
          },
          shape = CardShape,
          modifier = Modifier
            .padding(CardPaddingValues)
        )
      }
    ) {
      Icon(
        imageVector = ImageVector.vectorResource(R.drawable.question),
        contentDescription = "기술 스택 안내",
        tint = Color.LightGray,
        modifier = Modifier
          .clip(ChipShape)
          .scalableClick {
            scope.launch {
              tooltipState.show()
            }
          }
      )
    }
  }
}