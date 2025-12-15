package com.jiy.user.presentation

import com.jiy.user.domain.model.Skill
import com.jiy.user.domain.model.SkillLevel
import com.jiy.user.domain.model.User
import kotlin.random.Random

object UserDummy {
  fun userWithNetwork(): User {
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
  fun user(): User {
    return User(
      id = Random.nextInt(),
      name = "name",
      birth = "1998.11.04",
      phoneNum = "01012345678",
      email = "xyz@gmail.com",
      githubUrl = "https://naver.com",
      profileImageUrl = "/Users/jiy/Desktop/image.jpg",
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
}