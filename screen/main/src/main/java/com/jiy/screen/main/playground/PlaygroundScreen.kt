package com.jiy.screen.main.playground

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jiy.ui.content.LoadingContent
import com.jiy.ui.state.StatefulContent
import com.jiy.ui.theme.Padding
import com.joy.toy.domain.toy_core.model.ToyListItem
import com.joy.toy.presentation.component.ToyListItemComponent
import com.joy.toy.presentation.dummy.ToyDummy

@Composable
fun PlaygroundScreen(
  onToyClick: (id: Int) -> Unit,
  onMoreToyClick: () -> Unit,
  modifier: Modifier = Modifier,
  viewModel: PlaygroundViewModel = hiltViewModel(),
) {
  val toyListState by viewModel.toyListState.collectAsStateWithLifecycle()

  Scaffold(
    topBar = {
      TopAppBar(
        title = {
          Column {
            Text(text = "놀이터", style = MaterialTheme.typography.headlineMedium)
            Text(text = "직헙 체험할 수 있는 기능 데모 및 실험실", style = MaterialTheme.typography.titleSmall)
          }
        }
      )
    },
    modifier = modifier
  ) { inner ->
    StatefulContent(
      state = toyListState,
      loadingContent = { LoadingContent(Modifier.fillMaxSize()) },
      errorContent = { Text(it.stackTraceToString()) },
      successContent = { list ->
        PlaygroundContent(
          toyList = list,
          onToyClick = onToyClick,
          onMoreToyClick = onMoreToyClick,
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
private fun PlaygroundContent(
  toyList: List<ToyListItem>,
  onToyClick: (id: Int) -> Unit,
  onMoreToyClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    verticalArrangement = Arrangement.spacedBy(12.dp),
    modifier = modifier
      .verticalScroll(rememberScrollState())
      .padding(Padding.Screen.Inset)
  ) {
    toyList.forEach { item ->
      ToyListItemComponent(
        item = item,
        onClick = { onToyClick(item.id) }
      )
    }
    Button(
      onClick = onMoreToyClick,
      modifier = Modifier.fillMaxWidth()
    ) {
      Text(
        text = "더 많은 장난감 보기",
        style = MaterialTheme.typography.bodyMedium
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun PlaygroundContentPreview() {
  MaterialTheme {
    PlaygroundContent(
      toyList = ToyDummy.toyList(5),
      onToyClick = {},
      onMoreToyClick = {},
      modifier = Modifier.fillMaxSize()
    )
  }
}