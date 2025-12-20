package com.joy.toy.domain.toy_core.model

/**
 * 장난감 상세 모델
 *
 * @property id 리소스 아이디
 * @property toyId 장난감 아이디
 * @property name 장난감 이름
 * @property description 장난감 설명
 * @property thumbnailUrl 장난감 썸네일 이미지
 * @property images 장난감 이미지 목록
 * @property keywords 장난감 키워드 목록
 * @property codes 장난감 코드 목록
 * @property version 장난감 버전
 * @property gitHubLink 장난감 코드의 깃헙 링크
 */
data class ToyDetail(
  val id: Int,
  val toyId: Int,
  val name: String,
  val description: String,
  val thumbnailUrl: String,
  val images: List<ToyImage>,
  val keywords: List<String>,
  val codes: List<ToyCode>,
  val version: ToyVersion,
  val gitHubLink: String,
)
