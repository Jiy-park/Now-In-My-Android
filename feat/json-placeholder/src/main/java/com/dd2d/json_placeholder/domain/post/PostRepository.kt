package com.dd2d.json_placeholder.domain.post

import com.dd2d.json_placeholder.domain.post.model.Post
import com.dd2d.json_placeholder.domain.post.model.PostCreateData
import com.dd2d.json_placeholder.domain.post.model.PostUpdateData

interface PostRepository {
  suspend fun getPostList(): List<Post>
  suspend fun getPost(id: Int): Post
  suspend fun createPost(data: PostCreateData): Post
  suspend fun updatePost(id: Int, data: PostUpdateData): Post
  suspend fun deletePost(id: Int)
}