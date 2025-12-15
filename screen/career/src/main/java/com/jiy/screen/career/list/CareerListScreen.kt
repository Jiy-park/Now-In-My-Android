package com.jiy.screen.career.list

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jiy.career.domain.model.CareerListItem
import com.jiy.career.presentation.component.CareerListComponentPlaceholder
import com.jiy.career.presentation.component.CareerListItemComponent
import com.jiy.core.state.Stateful
import com.jiy.screen.career.list.component.UserBottomSheet
import com.jiy.ui.component.ShimmerBox
import com.jiy.ui.component.TopBar
import com.jiy.ui.modifier.scalableClick
import com.jiy.ui.theme.Padding
import com.jiy.ui.theme.Spacing
import com.jiy.user.domain.model.User
import com.jiy.user.presentation.ProfileImage

@Composable
fun CareerListScreen(
  onBack: () -> Unit,
  onCareerClick: (id: Int) -> Unit,
  modifier: Modifier = Modifier,
  viewModel: CareerListViewModel = hiltViewModel()
) {
  val userState by viewModel.userState.collectAsStateWithLifecycle()
  val careerListState by viewModel.careerListState.collectAsStateWithLifecycle()

  var openUserDialog by remember { mutableStateOf<User?>(null) }

  BackHandler(onBack = onBack)
  Scaffold(
    topBar = {
      TopBar(
        title = "경력",
        onBack = onBack,
        actions = {
          when(val state= userState) {
            is Stateful.Loading -> ShimmerBox(shape = CircleShape, size = 36.dp)
            is Stateful.Error -> {}
            is Stateful.Success -> {
              ProfileImage(
                image = state.data.profileImageUrl,
                modifier = Modifier
                  .size(36.dp)
                  .scalableClick(shape = CircleShape) { openUserDialog = state.data }
              )
            }
          }
        }
      )
    },
    modifier = modifier
  ) { inner ->
    CareerListContent(
      careerListState = careerListState,
      onCareerClick = onCareerClick,
      modifier = Modifier
        .consumeWindowInsets(inner)
        .fillMaxSize()
        .padding(inner)
    )
  }

  openUserDialog?.let { user ->
    UserBottomSheet(
      onDismissRequest = { openUserDialog = null },
      user = user ,
    )
  }
}

@Composable
private fun CareerListContent(
  careerListState: Stateful<List<CareerListItem>>,
  onCareerClick: (id: Int) -> Unit,
  modifier: Modifier = Modifier
) {
  LazyColumn(
    verticalArrangement = Arrangement.spacedBy(Spacing.Medium),
    contentPadding = Padding.Screen.Inset,
    modifier = modifier
  ) {
    when(careerListState) {
      is Stateful.Error -> {}
      is Stateful.Loading -> {
        item("placeholder") {
          CareerListComponentPlaceholder(Modifier.animateItem())
        }
      }
      is Stateful.Success -> {
        items(items = careerListState.data, key = CareerListItem::id) { item ->
          CareerListItemComponent(
            career = item,
            onClick = { onCareerClick(item.id) },
            modifier = Modifier
              .animateItem()
              .fillMaxWidth()
          )
        }
      }
    }
  }
}