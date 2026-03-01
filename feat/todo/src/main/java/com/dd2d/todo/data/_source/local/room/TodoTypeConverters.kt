package com.dd2d.todo.data._source.local.room

import androidx.room.TypeConverter
import com.dd2d.todo.domain.model.TodoPriority
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import kotlin.uuid.Uuid

/**
 * Room 데이터베이스에서 복합 타입을 저장하기 위한 타입 컨버터 클래스입니다.
 */
class TodoTypeConverters {
  private val formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME

  @TypeConverter
  fun fromUuid(value: Uuid?): String? = value?.toString()

  @TypeConverter
  fun toUuid(value: String?): Uuid? = value?.let { Uuid.parse(it) }

  @TypeConverter
  fun fromZonedDateTime(value: ZonedDateTime?): String? = value?.format(formatter)

  @TypeConverter
  fun toZonedDateTime(value: String?): ZonedDateTime? = value?.let { ZonedDateTime.parse(it, formatter) }

  @TypeConverter
  fun fromTodoPriority(value: TodoPriority?): String? = value?.name

  @TypeConverter
  fun toTodoPriority(value: String?): TodoPriority? = value?.let { TodoPriority.valueOf(it) }
}
