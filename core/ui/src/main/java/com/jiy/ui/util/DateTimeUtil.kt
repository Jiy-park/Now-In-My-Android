package com.jiy.ui.util

import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAccessor

/**
 * 날짜, 시간, 오프셋 등을 [pattern] 양식으로 포맷팅한 후 문자열로 반환합니다.
 */
fun TemporalAccessor.format(pattern: String): String {
  return DateTimeFormatter
    .ofPattern(pattern)
    .format(this)
}