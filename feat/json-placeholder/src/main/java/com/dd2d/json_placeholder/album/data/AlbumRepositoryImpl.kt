package com.dd2d.json_placeholder.album.data

import com.dd2d.json_placeholder._core.data.getUserNameById
import com.dd2d.json_placeholder._core.data.getUserProfileImageById
import com.dd2d.json_placeholder.album.data._source.remote.AlbumApi
import com.dd2d.json_placeholder.album.domain.AlbumRepository
import com.dd2d.json_placeholder.album.domain.model.Album
import com.dd2d.json_placeholder.album.domain.model.AlbumAuthor
import com.dd2d.json_placeholder.album.domain.model.AlbumPhoto
import retrofit2.HttpException
import javax.inject.Inject

internal class AlbumRepositoryImpl @Inject constructor(
  private val albumApi: AlbumApi,
): AlbumRepository {
  override suspend fun getAlbumList(authorId: Int?): List<Album> {
    val response = albumApi.getAlbums(userId = authorId)
    if(!response.isSuccessful) {
      throw HttpException(response)
    }
    return response.body()
      ?.let { dtoList ->
        dtoList.map { dto ->
          Album(
            id = dto.id,
            title = dto.title,
            author = AlbumAuthor(
              id = dto.userId,
              nickname = getUserNameById(dto.userId),
              profileImageUrl = getUserProfileImageById(dto.userId),
            )
          )
        }
      }
      ?: throw IllegalStateException("Response body is null")
  }

  override suspend fun getAlbum(albumId: Int): Album {
    val response = albumApi.getAlbum(albumId)
    if(!response.isSuccessful) {
      throw HttpException(response)
    }
    return response.body()
      ?.let { dto ->
        Album(
          id = dto.id,
          title = dto.title,
          author = AlbumAuthor(
            id = dto.userId,
            nickname = getUserNameById(dto.userId),
            profileImageUrl = getUserProfileImageById(dto.userId),
          )
        )
      }
      ?: throw IllegalStateException("Response body is null")
  }

  override suspend fun getAlbumPhotoList(albumId: Int): List<AlbumPhoto> {
    val response = albumApi.getAlbumPhotoList(albumId)
    if(!response.isSuccessful) {
      throw HttpException(response)
    }
    return response.body()
      ?.let { dtoList ->
        dtoList.map { dto ->
          AlbumPhoto(
            id = dto.id,
            title = dto.title,
            url = dto.url,
            thumbnailUrl = dto.thumbnailUrl,
          )
        }
      }
      ?: throw IllegalStateException("Response body is null")
  }
}