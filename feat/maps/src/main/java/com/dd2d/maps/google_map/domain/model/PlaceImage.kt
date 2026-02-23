package com.dd2d.maps.google_map.domain.model

import com.dd2d.network.BuildConfig

/**
 * 장소 이미지
 *
 * @property placeId 장소 아이디
 * @property name 이미지 이름
 * @property originWidth 원본 이미지의 가로 크기(px)
 * @property originHeight 원본 이미지의 세로 크기(px)
 */
data class PlaceImage(
  val placeId: String,
  val name: String,
  val originWidth: Int?,
  val originHeight: Int?,
) {
  /** 주어진 [width]와 [height]의 이미지 `url`을 반환합니다. */
  fun url(width: Int, height: Int): String = BuildConfig.GOOGLE_PLACE_BASE_URL +
      "v1/" +
      "$name/" +
      "media?" +
      "maxWidthPx=$width&" +
      "maxHeightPx=$height&" +
      "key=${BuildConfig.GOOGLE_PLACE_API_KEY}"

  /** 주어진 [size]의 이미지 `url`을 반환합니다. */
  fun url(size: Int): String = url(size, size)
}