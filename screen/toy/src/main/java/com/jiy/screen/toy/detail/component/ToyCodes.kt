package com.jiy.screen.toy.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jiy.ui.component.SectionLabel
import com.jiy.ui.modifier.scalableClick
import com.jiy.ui.theme.Padding
import com.jiy.ui.theme.Shape
import com.jiy.ui.theme.Spacing
import com.joy.toy.domain.toy_core.model.ToyCode
import dev.snipme.highlights.Highlights
import dev.snipme.highlights.model.SyntaxLanguage
import dev.snipme.highlights.model.SyntaxThemes
import dev.snipme.kodeview.view.CodeTextView

@Composable
internal fun ToyCodes(
  codes: List<ToyCode>,
  onCodeClick: (index: Int, code: ToyCode) -> Unit,
  modifier: Modifier = Modifier
) {
  val pagerState = rememberPagerState {codes.size }
  Column(modifier = modifier) {
    SectionLabel("코드 (${pagerState.currentPage + 1}/${codes.size})")
    HorizontalPager(
      state = pagerState,
      pageSize = PageSize.Fixed(270.dp),
      pageSpacing = Spacing.Large,
      verticalAlignment = Alignment.Top,
      key = { codes[it].id },
      modifier = Modifier
        .padding(top = Spacing.Small)
        .fillMaxWidth()
    ) { page ->
      val code = codes[page]
      val highlights = remember {
        Highlights.Builder(code = code.code)
          .language(SyntaxLanguage.KOTLIN)
          .theme(SyntaxThemes.darcula())
          .build()
      }
      CodeComponent(
        highlights = highlights,
        description = code.description,
        onClick = { onCodeClick(page, code) },
        modifier = Modifier
          .fillMaxWidth()
          .heightIn(max = 400.dp)
      )
    }
  }
}

@Composable
private fun CodeComponent(
  highlights: Highlights,
  description: String,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  Column(
    modifier = modifier
      .scalableClick(onClick = onClick, shape = Shape.Card)
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .background(color = MaterialTheme.colorScheme.surfaceVariant, shape = Shape.Card)
        .border(width = 1.dp, color = MaterialTheme.colorScheme.outlineVariant, shape = Shape.Card)
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = Padding.S, vertical = Padding.XS)
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(Spacing.ExtraSmall),
        ) {
          listOf(c1, c2, c3).forEach { color ->
            Box(Modifier.size(10.dp).background(color, CircleShape))
          }
        }
        Text(
          text = highlights.getLanguage().name.lowercase(),
          style = MaterialTheme.typography.bodySmall
        )
      }
      HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
      CodeTextView(
        highlights = highlights,
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = Padding.S, vertical = Padding.XS)
      )
    }
    Text(
      text = description,
      style = MaterialTheme.typography.bodyMedium,
      modifier = Modifier.padding(top = Padding.S)
    )
  }
}

val c1 = Color(0xFFFF6454)
val c2 = Color(0xFFFFCE00)
val c3 = Color(0xFF20AF54)