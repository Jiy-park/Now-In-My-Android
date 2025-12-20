package com.joy.toy.domain.toy_core.model

/**
 * 장난감 이미지 모델
 *
 * @property id 리소스 아이디
 * @property toyResourceId 장난감 리소스 아이디. `toyId`는 장난감 간의 구분을 위한 아이디입니다.
 * [toyResourceId]는 하나의 장난감에서의 버전 구분을 위한 아이디이며, [ToyDetail.id], [ToyListItem.id]을 의미합니다.
 * 이는 데이터베이스에서 리소스 구분용 아이디와 같습니다.
 * @property code 장난감의 코드
 * @property description 코드 설명
 */
data class ToyCode(
  val id: Int,
  val toyResourceId: Int,
  val code: String,
  val description: String,
)
