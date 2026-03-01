package com.dd2d.todo.data._source.local.room.relation

import androidx.room.ColumnInfo
import androidx.room.Embedded

/**
 * Todo 항목과 카테고리 정보, 그리고 하위 Todo 존재 여부 플래그를 포함하는 POJO 클래스입니다.
 */
data class SubTodoWithChildren(
  @Embedded
  val todoWithCategory: TodoWithCategory,
  @ColumnInfo(name = "has_sub_todos")
  val hasSubTodos: Boolean
)
