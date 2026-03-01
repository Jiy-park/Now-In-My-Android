package com.dd2d.todo.data._source.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.dd2d.todo.data._source.local.room.entity.TodoEntity
import com.dd2d.todo.data._source.local.room.relation.SubTodoWithChildren
import com.dd2d.todo.data._source.local.room.relation.TodoWithCategory
import com.dd2d.todo.domain.model.TodoPriority
import kotlin.uuid.Uuid

/**
 * Todo 테이블에 접근하기 위한 Data Access Object입니다.
 */
@Dao
interface TodoDao {
  /**
   * 필터 조건에 맞는 최상위(Root) Todo 목록을 조회합니다. (카테고리 정보 포함)
   * 각 Todo의 하위 항목 존재 여부를 함께 반환합니다.
   */
  @Transaction
  @Query("""
    SELECT *, (SELECT COUNT(*) FROM todos AS sub WHERE sub.parentId = t.id) > 0 AS has_sub_todos 
    FROM todos AS t
    WHERE (:categoryId IS NULL OR t.categoryId = :categoryId) 
    AND (:priority IS NULL OR t.priority = :priority)
    AND t.parentId IS NULL
  """)
  suspend fun getRootTodosWithChildrenFlag(categoryId: Uuid?, priority: TodoPriority?): List<SubTodoWithChildren>

  /**
   * 필터 조건에 맞는 최상위(Root) Todo 목록을 조회합니다. (카테고리 정보 포함)
   * categoryId와 priority가 null인 경우 해당 필터는 무시됩니다.
   */
  @Transaction
  @Query("""
    SELECT * FROM todos 
    WHERE (:categoryId IS NULL OR categoryId = :categoryId) 
    AND (:priority IS NULL OR priority = :priority)
    AND parentId IS NULL
  """)
  suspend fun getRootTodos(categoryId: Uuid?, priority: TodoPriority?): List<TodoWithCategory>

  /** 특정 ID의 Todo를 조회합니다. (카테고리 정보 포함) */
  @Transaction
  @Query("SELECT * FROM todos WHERE id = :id")
  suspend fun getTodo(id: Uuid): TodoWithCategory?

  /** 여러 ID의 Todo들을 한 번에 조회합니다. */
  @Transaction
  @Query("SELECT * FROM todos WHERE id IN (:ids)")
  suspend fun getTodos(ids: List<Uuid>): List<TodoWithCategory>

  /** 새로운 Todo를 삽입하거나 기존 Todo를 교체합니다. */
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertTodo(todo: TodoEntity)

  /** 기존 Todo 정보를 수정합니다. */
  @Update
  suspend fun updateTodo(todo: TodoEntity)

  /** 여러 Todo 정보를 일괄 수정합니다. */
  @Update
  suspend fun updateTodos(todos: List<TodoEntity>)

  /** Todo를 삭제합니다. */
  @Delete
  suspend fun deleteTodo(todo: TodoEntity)

  /** 특정 부모 Todo에 속한 하위 Todo 목록을 조회합니다. */
  @Transaction
  @Query("SELECT * FROM todos WHERE parentId = :parentId")
  suspend fun getSubTodos(parentId: Uuid): List<TodoWithCategory>

  /** 특정 부모 Todo에 속한 하위 Todo 목록을 조회하되, 각 항목의 하위 존재 여부를 포함합니다. */
  @Transaction
  @Query("""
    SELECT *, (SELECT COUNT(*) FROM todos AS sub WHERE sub.parentId = t.id) > 0 AS has_sub_todos
    FROM todos AS t
    WHERE t.parentId = :parentId
  """)
  suspend fun getSubTodosWithChildrenFlag(parentId: Uuid): List<SubTodoWithChildren>

  /** 특정 Todo의 직계 하위 Todo ID 목록을 조회합니다. */
  @Query("SELECT id FROM todos WHERE parentId = :parentId")
  suspend fun getDirectSubTodoIds(parentId: Uuid): List<Uuid>
}
