package com.dd2d.json_placeholder.post.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dd2d.core.stateful.Stateful
import com.dd2d.core.stateful.statefulFlow
import com.dd2d.json_placeholder.post.domain.PostRepository
import com.dd2d.json_placeholder.post.domain.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
  postRepository: PostRepository
) : ViewModel() {
  val postListState: StateFlow<Stateful<List<Post>>> = statefulFlow { postRepository.getPostList() }
    .stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5000),
      initialValue = Stateful.Loading
    )
}