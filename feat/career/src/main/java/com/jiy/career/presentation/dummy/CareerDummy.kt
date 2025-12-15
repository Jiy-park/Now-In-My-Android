package com.jiy.career.presentation.dummy

import com.jiy.career.domain.model.CareerDetail
import com.jiy.career.domain.model.CareerListItem
import com.jiy.career.domain.model.CareerSkill
import java.time.LocalDate

object CareerDummy {
  fun careerListItem(id: Int = 1): CareerListItem {
    return CareerListItem(
      id = id,
      companyName = "companyName$id",
      companyImageUrl = "https://image.utoimage.com/preview/cp872722/2022/12/202212008462_500.jpg",
      position = "position",
      startDate = LocalDate.now(),
      endDate = listOf(LocalDate.now(), null).random(),
      skills = List((1..5).random()) {
        CareerSkill(id = it, "skill$it")
      },
    )
  }
  fun careerList(size: Int = (1..5).random()): List<CareerListItem> {
    return List(size) { careerListItem(it) }
  }
  fun careerDetail(): CareerDetail {
    return CareerDetail(
      id = 1,
      companyName = "companyName",
      companyImageUrl = "https://image.utoimage.com/preview/cp872722/2022/12/202212008462_500.jpg",
      position = "position",
      startDate = LocalDate.now(),
      endDate = listOf(LocalDate.now(), null).random(),
      skills = List((1..5).random()) {
        CareerSkill(id = it, "skill$it")
      },
      description = """
        경력 상세
        
        두번째 내용
        
        ....
      """.trimIndent(),
    )
  }
}