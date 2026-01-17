package com.dd2d.network

import kotlinx.serialization.json.Json

object NetworkDefault {
  val json = Json { 
    ignoreUnknownKeys = true  // 역직렬화 시 알 수 없는 키를 무시 (false면 예외 발생)
    prettyPrint = false       // JSON 출력 시 들여쓰기 및 줄바꿈 여부 (디버깅 시 true로 변경)
    isLenient = true          // 비표준 JSON 허용 (따옴표 없는 문자열, 주석 등)
    coerceInputValues = true  // null 값을 기본값으로 변환 (non-null 프로퍼티에 null이 올 경우)
    encodeDefaults = false    // 기본값을 가진 프로퍼티도 직렬화할지 여부
    explicitNulls = false     // 직렬화 시 null인 필드를 JSON에 포함할지 여부 (false면 null 필드를 JSON에서 생략, true면 "field": null로 명시)
  }
}