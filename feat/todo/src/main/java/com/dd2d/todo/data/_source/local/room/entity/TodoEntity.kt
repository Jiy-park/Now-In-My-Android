package com.dd2d.todo.data._source.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dd2d.todo.domain.model.TodoPriority
import com.dd2d.todo.domain.model.TodoState
import java.time.ZonedDateTime
import kotlin.uuid.Uuid

@Entity(tableName = "todos")
data class TodoEntity(
  @PrimaryKey val id: Uuid,
  val title: String,
  val content: String?,
  val state: TodoState,
  val priority: TodoPriority,
  val categoryId: Uuid,
  val deadline: ZonedDateTime?,
  val createdAt: ZonedDateTime,
  val updatedAt: ZonedDateTime,
  val parentId: Uuid?,
)
