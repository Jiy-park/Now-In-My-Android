package com.dd2d.todo.data._source.local.room.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.dd2d.todo.data._source.local.room.entity.CategoryEntity
import com.dd2d.todo.data._source.local.room.entity.TodoEntity

/**
 * Todo 항목과 해당 항목의 카테고리 정보를 함께 포함하는 관계 데이터 클래스입니다.
 */
data class TodoWithCategory(
  @Embedded val todo: TodoEntity,
  @Relation(
    parentColumn = "categoryId",
    entityColumn = "id"
  )
  val category: CategoryEntity
)
