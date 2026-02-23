package com.dd2d.todo.data._source.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dd2d.todo.domain.model.TodoPriority
import java.time.ZonedDateTime
import kotlin.uuid.Uuid

/**
 * Todo 항목 정보를 저장하는 Room 엔티티 클래스입니다.
 */
@Entity(tableName = "todos")
data class TodoEntity(
  @PrimaryKey val id: Uuid,
  val title: String,
  val content: String?,
  val priority: TodoPriority,
  val categoryId: Uuid,
  val deadline: ZonedDateTime?,
  val createdAt: ZonedDateTime,
  val updatedAt: ZonedDateTime,
  val completedAt: ZonedDateTime?,
  val parentId: Uuid?,
)
