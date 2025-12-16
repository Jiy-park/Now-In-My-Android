package com.jiy.career.domain.model

import java.time.LocalDate

/**
 * 경력 모델 항목 모델
 *
 * @property id 경력 리소스 아이디
 * @property companyName 회사명
 * @property companyImageUrl 회사 대표 이미지
 * @property position 회사 내 포지션 ex)개발팀, 사원 등
 * @property startDate 입사일
 * @property endDate 퇴사일. 아직 재직중인 경우 `null`
 * @property skills 회사에서 사용하던 기술 목록
 */
data class CareerListItem(
  val id: Int,
  val companyName: String,
  val companyImageUrl: String?,
  val position: String,
  val startDate: LocalDate,
  val endDate: LocalDate?,
  val skills: List<CareerSkill>,
)