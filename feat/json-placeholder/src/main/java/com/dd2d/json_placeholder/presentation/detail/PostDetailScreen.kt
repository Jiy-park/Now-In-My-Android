package com.dd2d.json_placeholder.presentation.detail

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dd2d.json_placeholder.domain.post.model.Post
import com.dd2d.json_placeholder.presentation._core.content.StatefulContent
import com.dd2d.json_placeholder.presentation.detail.component.PostAuthorComponent
import com.dd2d.json_placeholder.presentation.detail.component.TopBar

@Composable
fun PostDetailScreen(
  onBack: () -> Unit,
  onUserClick: (userId: Int) -> Unit,
  modifier: Modifier = Modifier,
  viewModel: PostDetailViewModel = hiltViewModel()
) {
  val postDetailState by viewModel.postDetailState.collectAsStateWithLifecycle()

  BackHandler(onBack = onBack)
  Scaffold(
    topBar = { TopBar(title = "Post 상세", onBack = onBack) },
    modifier = modifier
  ) { inner ->
    StatefulContent(
      state = postDetailState,
      errorMessage = { "Post를 불러오지 못했습니다." },
      modifier = Modifier
        .consumeWindowInsets(inner)
        .fillMaxSize()
        .padding(inner)
    ) { postDetail ->
      PostDetailContent(
        postDetail = postDetail,
        onPostAuthorClick = onUserClick,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 20.dp),
        modifier = Modifier.fillMaxSize()
      )
    }
  }
}

@Composable
private fun PostDetailContent(
  postDetail: Post,
  onPostAuthorClick: (authorId: Int) -> Unit,
  modifier: Modifier = Modifier,
  contentPadding: PaddingValues = PaddingValues()
) {
  Column(
    modifier = modifier
      .verticalScroll(rememberScrollState())
      .padding(contentPadding)
  ) {
    PostAuthorComponent(
      author = postDetail.author,
      onClick = { onPostAuthorClick(postDetail.author.id) },
      contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
    )
    Text(
      text = postDetail.title,
      fontWeight = FontWeight.W600,
      color = MaterialTheme.colorScheme.onSurface,
      fontSize = 18.sp,
      lineHeight = 1.4.em,
    )
    HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
    Text(
      text = postDetail.body,
      fontWeight = FontWeight.W400,
      color = MaterialTheme.colorScheme.onSurface,
      fontSize = 14.sp,
      lineHeight = 1.4.em,
    )
  }
}