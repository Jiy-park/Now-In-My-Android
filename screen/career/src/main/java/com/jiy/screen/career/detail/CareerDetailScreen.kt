package com.jiy.screen.career.detail

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jiy.career.domain.model.CareerDetail
import com.jiy.career.presentation.dummy.CareerDummy
import com.jiy.core.state.Stateful
import com.jiy.screen.career.detail.component.CareerSkillStack
import com.jiy.screen.career.detail.component.CompanyComponent
import com.jiy.screen.career.detail.content.CareerDetailLoadingContent
import com.jiy.ui.R
import com.jiy.ui.component.SectionLabel
import com.jiy.ui.component.TopBar
import com.jiy.ui.theme.Padding
import com.jiy.ui.theme.Shape
import com.jiy.ui.theme.Spacing

@Composable
fun CareerDetailScreen(
  onBack: () -> Unit,
  modifier: Modifier = Modifier,
  viewModel: CareerDetailViewModel = hiltViewModel()
) {
  val careerDetailState by viewModel.careerDetailState.collectAsStateWithLifecycle()

  BackHandler(onBack = onBack)
  Scaffold(
    topBar = { TopBar(title = "경력 상세", onBack = onBack) },
    modifier = modifier
  ) { inner ->
    val innerModifier = Modifier
      .consumeWindowInsets(inner)
      .fillMaxSize()
      .padding(inner)

    when (val state = careerDetailState) {
      is Stateful.Loading -> CareerDetailLoadingContent(innerModifier)
      is Stateful.Error -> Text(text = "경력 정보를 조회할지 못했습니다.\n${state.exception.mainMessage}")
      is Stateful.Success -> {
        CareerDetailContent(
          career = state.data,
          modifier = innerModifier,
        )
      }
    }
  }
}

@Composable
private fun CareerDetailContent(
  career: CareerDetail,
  modifier: Modifier = Modifier
) {
  Column(
    verticalArrangement = Arrangement.spacedBy(Spacing.Large),
    modifier = modifier
      .fillMaxSize()
      .verticalScroll(rememberScrollState())
      .padding(Padding.Screen.Inset),
  ) {
    CompanyComponent(
      companyName = career.companyName,
      companyImageUrl = career.companyImageUrl,
      position = career.position,
      startDate = career.startDate,
      endDate = career.endDate,
      modifier = Modifier.fillMaxWidth(),
    )
    Column(verticalArrangement = Arrangement.spacedBy(Spacing.Small)) {
      SectionLabel(label = "사용 기술", vectorIconRes = R.drawable.code)
      CareerSkillStack(
        skills = career.skills,
        modifier = Modifier.fillMaxWidth(),
      )
    }
    Column(verticalArrangement = Arrangement.spacedBy(Spacing.Small)) {
      SectionLabel(label = "주요 업무", vectorIconRes = com.jiy.screen.career.R.drawable.work)
      Text(
        text = career.description,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier
          .fillMaxWidth()
          .background(MaterialTheme.colorScheme.surface, Shape.Card)
          .padding(Spacing.Medium)
      )
    }
  }
}

@Preview
@Composable
private fun CareerDetailContentPrev() {
  Column(Modifier.fillMaxSize().background(Color.White)) {
    CareerDetailContent(
      career = CareerDummy.careerDetail(),
      modifier = Modifier
    )
  }
}