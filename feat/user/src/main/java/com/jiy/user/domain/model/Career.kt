package com.jiy.user.domain.model

import java.time.LocalDate

data class Career(
  val id: Int,
  val companyName: String,
  val position: String,
  val startDate: LocalDate,
  val endDate: LocalDate?,
  val description: String,
)