package com.dd2d.todo.data._source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dd2d.todo.data._source.local.room.dao.CategoryDao
import com.dd2d.todo.data._source.local.room.dao.TodoDao
import com.dd2d.todo.data._source.local.room.entity.CategoryEntity
import com.dd2d.todo.data._source.local.room.entity.TodoEntity

/**
 * Todo 기능에서 사용하는 Room 데이터베이스 클래스입니다.
 */
@Database(
  entities = [
    TodoEntity::class,
    CategoryEntity::class
  ],
  version = 1,
  exportSchema = false
)
@TypeConverters(TodoTypeConverters::class)
abstract class TodoDatabase : RoomDatabase() {
  /** Todo 관련 데이터 조작을 위한 DAO */
  abstract fun todoDao(): TodoDao

  /** 카테고리 관련 데이터 조작을 위한 DAO */
  abstract fun categoryDao(): CategoryDao

  companion object {
    /** 데이터베이스 파일 이름 */
    const val DATABASE_NAME = "todo-database"
  }
}
