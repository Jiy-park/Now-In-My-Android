package com.dd2d.now_in_my_android.presentation.main.model

sealed interface MainScreenNavEvent {
  data class PostDetail(val postId: Int): MainScreenNavEvent
  data class AlbumDetail(val albumId: Int): MainScreenNavEvent
  data object Maps: MainScreenNavEvent
}