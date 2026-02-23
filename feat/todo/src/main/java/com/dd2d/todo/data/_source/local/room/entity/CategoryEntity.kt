package com.dd2d.todo.data._source.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.uuid.Uuid

@Entity(tableName = "categories")
data class CategoryEntity(
  @PrimaryKey val id: Uuid,
  val name: String,
  val colorHex: String,
)
