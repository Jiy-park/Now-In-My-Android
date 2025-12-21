package com.jiy.screen.toy.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jiy.screen.toy.detail.component.CodeLinkButton
import com.jiy.screen.toy.detail.component.ToyCodes
import com.jiy.screen.toy.detail.component.ToyImages
import com.jiy.screen.toy.detail.component.ToyKeywords
import com.jiy.screen.toy.detail.component.VersionComponent
import com.jiy.ui.component.TopBar
import com.jiy.ui.content.LoadingContent
import com.jiy.ui.state.StatefulContent
import com.jiy.ui.theme.Padding
import com.jiy.ui.theme.Spacing
import com.joy.toy.domain.toy_core.model.ToyCode
import com.joy.toy.domain.toy_core.model.ToyDetail
import com.joy.toy.domain.toy_core.model.ToyImage
import com.joy.toy.presentation.dummy.ToyDummy

@Composable
fun ToyDetailScreen(
  onBack: () -> Unit,
  onMoreVersionClick: () -> Unit,
  modifier: Modifier = Modifier,
  viewModel: ToyDetailViewModel = hiltViewModel()
) {
  val toyDetailState by viewModel.toyDetailState.collectAsStateWithLifecycle()

  Scaffold(
    topBar = { TopBar(onBack = onBack) },
    modifier = modifier
  ) { inner ->
    StatefulContent(
      state = toyDetailState,
      loadingContent = { LoadingContent(Modifier.fillMaxSize()) },
      errorContent = { Text(text = it.stackTraceToString()) },
      successContent = { toyDetail ->
        ToyDetailContent(
          toyDetail = toyDetail,
          onMoreVersionClick = onMoreVersionClick,
          onToyImageClick = { index, image -> },
          onToyCodeClick = { index, code ->  },
          onCodeLinkClick = {},
          modifier = Modifier
            .fillMaxSize()
        )
      },
      modifier = Modifier
        .consumeWindowInsets(inner)
        .fillMaxSize()
        .padding(inner)
    )
  }
}

@Composable
private fun ToyDetailContent(
  toyDetail: ToyDetail,
  onMoreVersionClick: () -> Unit,
  onToyImageClick: (index: Int, image: ToyImage) -> Unit,
  onToyCodeClick: (index: Int, code: ToyCode) -> Unit,
  onCodeLinkClick: (url: String) -> Unit,
  modifier: Modifier = Modifier,
  contentPadding: PaddingValues = Padding.Screen.Inset,
) {
  Column(
    verticalArrangement = Arrangement.spacedBy(Spacing.Large),
    modifier = modifier
      .verticalScroll(rememberScrollState())
      .padding(contentPadding)
  ) {
    VersionComponent(
      version = toyDetail.version,
      onMoreVersionClick = onMoreVersionClick,
      modifier = Modifier.fillMaxWidth(),
    )
    Text(
      text = toyDetail.name,
      style = MaterialTheme.typography.headlineMedium.copy(
        lineBreak = LineBreak.Heading
      ),
    )
    Text(
      text = toyDetail.description,
      style = MaterialTheme.typography.bodyLarge
    )
    ToyKeywords(
      keywords = toyDetail.keywords,
      modifier = Modifier.fillMaxWidth()
    )
    ToyImages(
      images = toyDetail.images,
      onImageClick = onToyImageClick,
      modifier = Modifier.fillMaxWidth(),
    )
    ToyCodes(
      codes = toyDetail.codes,
      onCodeClick = onToyCodeClick,
      modifier = Modifier.fillMaxWidth(),
    )
    CodeLinkButton(
      onClick = { onCodeLinkClick(toyDetail.gitHubLink) },
      modifier = Modifier.fillMaxWidth(),
    )
  }
}

@Preview
@Composable
private fun ToyDetailContentPrev() {
  ToyDetailContent(
    toyDetail = ToyDummy.toyDetail(),
    onMoreVersionClick = {},
    onToyImageClick = { _, _ -> },
    onToyCodeClick = { _, _ -> },
    onCodeLinkClick = {},
    modifier = Modifier
      .fillMaxSize()
      .background(Color.White)
  )
}