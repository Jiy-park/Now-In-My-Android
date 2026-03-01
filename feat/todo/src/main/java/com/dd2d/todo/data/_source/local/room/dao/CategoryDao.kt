package com.dd2d.todo.data._source.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dd2d.todo.data._source.local.room.entity.CategoryEntity
import kotlin.uuid.Uuid

/**
 * 카테고리 테이블에 접근하기 위한 Data Access Object입니다.
 */
@Dao
interface CategoryDao {
  /** 모든 카테고리를 조회합니다. */
  @Query("SELECT * FROM categories")
  suspend fun getCategories(): List<CategoryEntity>

  /** 특정 ID의 카테고리를 조회합니다. */
  @Query("SELECT * FROM categories WHERE id = :id")
  suspend fun getCategory(id: Uuid): CategoryEntity?

  /** 새로운 카테고리를 삽입하거나 기존 카테고리를 교체합니다. */
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertCategory(category: CategoryEntity)

  /** 기존 카테고리 정보를 수정합니다. */
  @Update
  suspend fun updateCategory(category: CategoryEntity)

  /** 카테고리를 삭제합니다. */
  @Delete
  suspend fun deleteCategory(category: CategoryEntity)
}
