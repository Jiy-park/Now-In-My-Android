package com.joy.toy.domain.toy_core.model

/**
 * 장난감 목록 조회용 옵션 모델
 *
 * @property page 조회할 페이지. `0`부터 시작
 * @property size 페이지당 아이템 갯수
 */
data class ToyListOption(
  val page: Int = 0,
  val size: Int = 15,
)
