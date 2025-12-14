package com.jiy.user.presentation

import com.jiy.user.domain.model.Career
import com.jiy.user.domain.model.Skill
import com.jiy.user.domain.model.SkillLevel
import com.jiy.user.domain.model.User
import java.time.LocalDate
import kotlin.random.Random

object UserDummy {
  fun user(): User {
    return User(
      id = Random.nextInt(),
      name = "name",
      birth = "1998.11.04",
      phoneNum = "01012345678",
      email = "xyz@gmail.com",
      githubUrl = "https://naver.com",
      profileImageUrl = "https://image.utoimage.com/preview/cp872722/2022/12/202212008462_500.jpg",
      shortIntroduction = "안녕하세요.",
    )
  }

  fun skill(): Skill {
    val level = SkillLevel.entries.random()
    return Skill(
      id = Random.nextInt(),
      name = "name$level",
      iconUrl = null,
      level = level,
      description = null,
    )
  }

  fun career(): Career {
    return Career(
      id = Random.nextInt(),
      companyName = "companyName",
      position = "position",
      startDate = LocalDate.now(),
      endDate = listOf(LocalDate.now(), null).random(),
      description = "경력 기술",
    )
  }
}