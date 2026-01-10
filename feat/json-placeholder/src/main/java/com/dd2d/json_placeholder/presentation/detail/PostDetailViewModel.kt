package com.dd2d.json_placeholder.presentation.detail


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.dd2d.core.stateful.Stateful
import com.dd2d.core.stateful.statefulFlow
import com.dd2d.json_placeholder.domain.post.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
  savedStateHandle: SavedStateHandle,
  postRepository: PostRepository
): ViewModel() {
  val postScreenRoute = savedStateHandle.toRoute<PostDetailScreenRoute>()

  val postDetailState = statefulFlow { postRepository.getPost(postScreenRoute.postId) }
    .stateIn(scope = viewModelScope, started = SharingStarted.WhileSubscribed(5000), initialValue = Stateful.Loading)
}