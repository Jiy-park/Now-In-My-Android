package com.jiy.career.domain

import com.jiy.career.domain.model.CareerDetail
import com.jiy.career.domain.model.CareerListItem
import com.jiy.career.domain.model.CareerListOption

interface CareerRepository {
  suspend fun getMyCareers(option: CareerListOption): List<CareerListItem>
  suspend fun getCareer(id: Int): CareerDetail
}