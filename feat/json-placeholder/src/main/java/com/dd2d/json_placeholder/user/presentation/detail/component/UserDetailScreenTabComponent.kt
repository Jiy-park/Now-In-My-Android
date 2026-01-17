package com.dd2d.json_placeholder.user.presentation.detail.component

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.dd2d.json_placeholder.user.presentation.detail.model.UserDetailScreenTab

@Composable
internal fun UserDetailScreenTabComponent(
  selectedTab: UserDetailScreenTab,
  onTabSelected: (UserDetailScreenTab) -> Unit,
  modifier: Modifier = Modifier,
  content: @Composable (UserDetailScreenTab) -> Unit
) {
  Column(modifier = modifier) {
    PrimaryTabRow(
      selectedTabIndex = selectedTab.ordinal,
      modifier = Modifier.fillMaxWidth(),
    ) {
      UserDetailScreenTab.entries.forEach { tab ->
        Tab(
          selected = selectedTab == tab,
          onClick = { onTabSelected(tab) },
        ) {
          Text(
            text = tab.label,
            fontWeight = FontWeight.W400,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 16.sp,
            lineHeight = 1.2.em,
            textAlign = TextAlign.Center,
            modifier = Modifier
              .weight(1F)
              .padding(vertical = 8.dp)
          )
        }
      }
    }
    Crossfade(targetState = selectedTab, content = content)
  }
}