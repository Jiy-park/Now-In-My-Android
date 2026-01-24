package com.dd2d.maps.google_map.domain.model

/**
 * 장소 간단 정보 모델
 *
 * @property id 장소 아이디
 * @property name 장소 표시 이름
 * @property fullAddress 장소의 주소
 * @property location 장소의 위도와 경도 좌표
 * @property types 장소의 유형 목록. ex): `food`, `store` 등
 * @property contact 장소의 전화번호. ex) `+82 32-719-2000`
 * @property rating 장소의 평점
 * @property userRatingCount [rating]에 기여한 사람 수
 * @property googleMapUri 장소의 Google Maps URI
 * @property websiteUrl 장소의 공식 웹사이트 URL
 * @property openNow 현재 영업 중인지 여부
 * @property weekdayDescriptions 요일별 영업 시간 설명 목록
 * @property images 장소와 관련된 이미지 목록
 */
data class Place(
  val id: String,
  val name: String?,
  val fullAddress: String?,
  val location: PlaceLocation?,
  val types: List<String>,
  val contact: String?,
  val rating: Double?,
  val userRatingCount: Int?,
  val googleMapUri: String?,
  val websiteUrl: String?,
  val openNow: Boolean?,
  val weekdayDescriptions: List<String>?,
  val images: List<PlaceImage>
)