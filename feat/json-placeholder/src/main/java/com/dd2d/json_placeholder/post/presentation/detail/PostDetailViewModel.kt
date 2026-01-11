package com.dd2d.json_placeholder.post.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.dd2d.core.flow.stateInWhileSubscribed
import com.dd2d.core.stateful.Stateful
import com.dd2d.core.stateful.statefulFlow
import com.dd2d.json_placeholder.post.domain.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
  savedStateHandle: SavedStateHandle,
  postRepository: PostRepository
): ViewModel() {
  val postScreenRoute = savedStateHandle.toRoute<PostDetailScreenRoute>()

  val postDetailState = statefulFlow { postRepository.getPost(postScreenRoute.postId) }
    .stateInWhileSubscribed(scope = viewModelScope, initialValue = Stateful.Loading)
}