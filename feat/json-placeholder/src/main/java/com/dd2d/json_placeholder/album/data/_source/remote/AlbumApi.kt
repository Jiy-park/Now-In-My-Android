package com.dd2d.json_placeholder.album.data._source.remote

import com.dd2d.json_placeholder.album.data._source.remote.dto.response.AlbumPhotoResponseDto
import com.dd2d.json_placeholder.album.data._source.remote.dto.response.AlbumResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface AlbumApi {
  @GET("albums")
  suspend fun getAlbumList(
    @Query("userId") userId: Int?,
  ): Response<List<AlbumResponseDto>>

  @GET("albums/{id}/photos")
  suspend fun getAlbumPhotoList(
    @Query("id") albumId: Int,
  ): Response<List<AlbumPhotoResponseDto>>
}