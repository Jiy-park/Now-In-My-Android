package com.jiy.career.domain.model

/**
 * 사내에세 사용하던 기술 스택
 *
 * @property id 기술 스택 아이디
 * @property name 기술 스택 이름
 * @constructor Create empty Skill
 */
data class CareerSkill(
  val id: Int,
  val name: String,
)