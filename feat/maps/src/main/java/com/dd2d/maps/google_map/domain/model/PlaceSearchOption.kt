package com.dd2d.maps.google_map.domain.model

/**
 * 장소 검색 옵션
 *
 * @property keyword 검색어
 * @property bias 검색 범위. 값이 주어지는 경우 [PlaceSearchBias]의 범위를 우선적으로 검색합니다.
 */
data class PlaceSearchOption(
  val keyword: String,
  val bias: PlaceSearchBias?,
)

/**
 * 우선 검색 범위 설정 모델
 *
 * latitude
 * @property centerLat 범위의 중심 위도
 * @property centerLng
 * @property radius
 * @constructor Create empty Place search bias
 */
data class PlaceSearchBias(
  val centerLat: Double,
  val centerLng: Double,
  val radius: Int,
)