package com.joy.toy.domain.toy_core.model

/**
 * 장난감 목록 항목 모델
 *
 * @property id 리소스 아이디
 * @property toyId 장난감 아이디. `Toy`는 버전이 존재합니다. 여러 버전이 있을 때 특정 장난감을 구분하기 위한 아이디입니다.
 * @property name 장난감 이름
 * @property description 장난감 설명
 * @property thumbnailUrl 장난감 썸네일 이미지
 * @property keywords 장난감 키워드 목록
 * @property version 장난감 버전
 */
data class ToyListItem(
  val id: Int,
  val toyId: Int,
  val name: String,
  val description: String,
  val thumbnailUrl: String,
  val keywords: List<String>,
  val version: String,
)