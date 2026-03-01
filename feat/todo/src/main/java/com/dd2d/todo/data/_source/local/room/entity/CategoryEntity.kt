package com.dd2d.todo.data._source.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.uuid.Uuid

/**
 * 카테고리 정보를 저장하는 Room 엔티티 클래스입니다.
 */
@Entity(tableName = "categories")
data class CategoryEntity(
  @PrimaryKey val id: Uuid,
  val name: String,
  val colorHex: String,
)
