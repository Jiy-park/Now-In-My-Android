package com.dd2d.json_placeholder.album.data._source.remote

import com.dd2d.json_placeholder.album.data._source.remote.dto.response.AlbumPhotoResponseDto
import com.dd2d.json_placeholder.album.data._source.remote.dto.response.AlbumResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface AlbumApi {
  @GET("albums")
  suspend fun getAlbums(
    @Query("userId") userId: Int?,
  ): Response<List<AlbumResponseDto>>

  @GET("albums/{id}")
  suspend fun getAlbum(
    @Path("id") albumId: Int,
  ): Response<AlbumResponseDto>

  @GET("albums/{id}/photos")
  suspend fun getAlbumPhotoList(
    @Path("id") albumId: Int,
  ): Response<List<AlbumPhotoResponseDto>>
}