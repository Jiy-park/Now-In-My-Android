package com.dd2d.json_placeholder.user.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import com.dd2d.json_placeholder.R
import com.dd2d.json_placeholder.user.domain.model.User
import com.dd2d.json_placeholder.user.presentation.detail.component.StatefulContent
import com.dd2d.json_placeholder.user.presentation.detail.component.TopBar

@Composable
fun UserDetailScreen(
  onBack: () -> Unit,
  modifier: Modifier = Modifier,
  viewModel: UserDetailViewModel = hiltViewModel()
) {
  val userDetailState by viewModel.userDetailState.collectAsStateWithLifecycle()

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
  modifier: Modifier = Modifier,
  contentPadding: PaddingValues = PaddingValues()
) {
  Column(
    verticalArrangement = Arrangement.spacedBy(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = modifier
      .padding(contentPadding)
  ) {
    AsyncImage(
      model = ImageRequest.Builder(LocalContext.current)
        .data(userDetail.profileImageUrl?: R.drawable.default_profile)
        .diskCachePolicy(CachePolicy.DISABLED)
        .memoryCachePolicy(CachePolicy.DISABLED)
        .size(200)
        .build(),
      contentDescription = "${userDetail.nickname} 프로필 이미지",
      contentScale = ContentScale.Crop,
      modifier = Modifier
        .clip(CircleShape)
        .size(64.dp)
    )
    Text(
      text = userDetail.nickname,
      fontWeight = FontWeight.W500,
      color = MaterialTheme.colorScheme.onSurface,
      fontSize = 18.sp,
      lineHeight = 1.4.em,
    )
  }
}