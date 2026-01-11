package com.dd2d.json_placeholder.user.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dd2d.core.stateful.Stateful
import com.dd2d.json_placeholder._core.ui.component.TopBar
import com.dd2d.json_placeholder._core.ui.content.StatefulContent
import com.dd2d.json_placeholder.post.domain.model.Post
import com.dd2d.json_placeholder.user.domain.model.User
import com.dd2d.json_placeholder.user.presentation.detail.component.UserDetailScreenTabComponent
import com.dd2d.json_placeholder.user.presentation.detail.component.UserPostListComponent
import com.dd2d.json_placeholder.user.presentation.detail.component.UserProfile
import com.dd2d.json_placeholder.user.presentation.detail.component.UserTodoListComponent
import com.dd2d.json_placeholder.user.presentation.detail.model.TodoListUIState
import com.dd2d.json_placeholder.user.presentation.detail.model.UserDetailScreenTab

@Composable
fun UserDetailScreen(
  onBack: () -> Unit,
  onPostClick: (id: Int) -> Unit,
  modifier: Modifier = Modifier,
  viewModel: UserDetailViewModel = hiltViewModel()
) {
  val userDetailState by viewModel.userDetailState.collectAsStateWithLifecycle()
  val todoListUIState by viewModel.todoListUIState.collectAsStateWithLifecycle()
  val postListState by viewModel.postListState.collectAsStateWithLifecycle()

  Scaffold(
    topBar = { TopBar(title = "User 상세", onBack = onBack) },
    modifier = modifier
  ) { inner ->
    StatefulContent(
      state = userDetailState,
      errorMessage = { "유저를 조회하지 못했습니다" },
      modifier = Modifier
        .consumeWindowInsets(inner)
        .fillMaxSize()
        .padding(inner)
    ) { userDetail ->
      UserDetailContent(
        userDetail = userDetail,
        todoListUIState = todoListUIState,
        onTodoListCompleteFilterChange = viewModel::changeTodoListCompleteFilter,
        postListState = postListState,
        onPostClick = onPostClick,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 20.dp),
        modifier = Modifier
          .fillMaxSize()
      )
    }
  }
}

@Composable
private fun UserDetailContent(
  userDetail: User,
  todoListUIState: Stateful<TodoListUIState>,
  onTodoListCompleteFilterChange: (Boolean?) -> Unit,
  postListState: Stateful<List<Post>>,
  onPostClick: (id: Int) -> Unit,
  modifier: Modifier = Modifier,
  contentPadding: PaddingValues = PaddingValues()
) {
  var selectedTab by rememberSaveable { mutableStateOf(UserDetailScreenTab.Todos) }

  Column(
    verticalArrangement = Arrangement.spacedBy(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = modifier
      .padding(contentPadding)
  ) {
    UserProfile(user = userDetail)
    UserDetailScreenTabComponent(
      selectedTab = selectedTab,
      onTabSelected = { selectedTab = it },
      modifier = Modifier
        .fillMaxWidth()
        .weight(1F)
    ) { tab ->
      when(tab) {
        UserDetailScreenTab.Todos -> {
          StatefulContent(
            state = todoListUIState,
            errorMessage = { "TODO 목록을 조회하지 못했습니다." },
            modifier = Modifier.fillMaxSize()
          ) { todoListUIState ->
            UserTodoListComponent(
              todoListUIState = todoListUIState,
              onFilterChange = onTodoListCompleteFilterChange,
              modifier = Modifier.fillMaxSize(),
            )
          }
        }

        UserDetailScreenTab.Posts -> {
          StatefulContent(
            state = postListState,
            errorMessage = { "Post 목록을 조회하지 못했습니다." },
            modifier = Modifier.fillMaxSize()
          ) { postList ->
            UserPostListComponent(
              postList = postList,
              onPostClick = onPostClick,
              modifier = Modifier.fillMaxSize(),
            )
          }
        }
      }
    }
  }
}