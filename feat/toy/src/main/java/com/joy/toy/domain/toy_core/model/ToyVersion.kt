package com.joy.toy.domain.toy_core.model

/**
 * 장난감 이미지 모델
 *
 * @property id 리소스 아이디
 * @property toyResourceId 장난감 리소스 아이디. `toyId`는 장난감 간의 구분을 위한 아이디입니다.
 * [toyResourceId]는 하나의 장난감에서의 버전 구분을 위한 아이디이며, [ToyDetail.id], [ToyListItem.id]을 의미합니다.
 * 이는 데이터베이스에서 리소스 구분용 아이디와 같습니다.
 * @property version 장난감 버전
 * @property releaseNote 버전 출시 내용
 * @property prevVersionId 이전 버전 리소스 아이디
 * @property nextVersionId 다음 버전 리소스 아이디
 */
data class ToyVersion(
  val id: Int,
  val toyResourceId: Int,
  val version: String,
  val releaseNote: String,
  val prevVersionId: Int?,
  val nextVersionId: Int?
)
