package com.dd2d.json_placeholder.album.domain

import com.dd2d.json_placeholder.album.domain.model.Album
import com.dd2d.json_placeholder.album.domain.model.AlbumPhoto

interface AlbumRepository {
  suspend fun getAlbumList(authorId: Int?): List<Album>
  suspend fun getAlbum(albumId: Int): Album

  suspend fun getAlbumPhotoList(albumId: Int): List<AlbumPhoto>
}