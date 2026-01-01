package com.jiy.screen.toy.list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jiy.ui.component.TopBar
import com.jiy.ui.content.LoadingContent
import com.jiy.ui.state.StatefulContent
import com.jiy.ui.theme.Padding
import com.joy.toy.domain.toy_core.model.ToyListItem
import com.joy.toy.presentation.component.ToyListItemComponent

@Composable
fun ToyListScreen(
  onBack: () -> Unit,
  onToyClick: (toyId: Int) -> Unit,
  modifier: Modifier = Modifier,
  viewModel: ToyListViewModel = hiltViewModel()
) {
  val toyListState by viewModel.toyList.collectAsStateWithLifecycle()

  Scaffold(
    topBar = { TopBar(onBack = onBack) },
    modifier = modifier
  ) { inner ->
    StatefulContent(
      state = toyListState,
      loadingContent = { LoadingContent(Modifier.fillMaxSize()) },
      errorContent = { Text(it.mainMessage) },
      successContent = { list ->
        ToyListContent(
          toyList = list,
          onToyClick = onToyClick,
          contentPadding = Padding.Screen.Inset,
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
private fun ToyListContent(
  toyList: List<ToyListItem>,
  onToyClick: (toyId: Int) -> Unit,
  contentPadding: PaddingValues,
  modifier: Modifier = Modifier,
) {
  LazyColumn(
    contentPadding = contentPadding,
    modifier = modifier
  ) {
    items(items = toyList, key = ToyListItem::id) { item ->
      ToyListItemComponent(
        item = item,
        onClick = { onToyClick(item.id) },
        modifier = Modifier
          .animateItem()
          .fillMaxWidth()
      )
    }
  }
}