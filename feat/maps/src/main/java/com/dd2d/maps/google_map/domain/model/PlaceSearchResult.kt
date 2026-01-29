package com.dd2d.maps.google_map.domain.model

/**
 * 장소 검색 결과
 *
 * @property id 장소 아이디
 * @property name 장소명
 * @property address 장소 주소
 */
data class PlaceSearchResult(
  val id: String,
  val name: String,
  val address: String,
)
