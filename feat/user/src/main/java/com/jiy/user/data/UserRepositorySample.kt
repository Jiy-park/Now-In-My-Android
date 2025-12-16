package com.jiy.user.data

import com.jiy.user.domain.UserRepository
import com.jiy.user.domain.model.Skill
import com.jiy.user.domain.model.SkillLevel
import com.jiy.user.domain.model.User
import kotlinx.coroutines.delay

internal object UserRepositorySample: UserRepository {
  override suspend fun me(): User {
    delay(100)
    return User(
      id = 1,
      name = "박지용",
      birth = "1998.11.04",
      phoneNum = "01039547706",
      email = "jiyong3954@gmail.com",
      githubUrl = "https://github.com/Jiy-park",
      profileImageUrl = "https://image.utoimage.com/preview/cp872722/2022/12/202212008462_500.jpg",
      shortIntroduction = "안녕하세요. 안드로이드 개발자 박지용입니다.",
    )
  }

  override suspend fun getMySkillStack(): List<Skill> {
    fun simpleSkill(id: Int, name: String, level: SkillLevel) = Skill(id = id, name = name, iconUrl = null, level = level,  description = null)
    delay(100)
    return listOf(
      simpleSkill(id = 1, name = "Android", level = SkillLevel.HIGH),
      simpleSkill(id = 2, name = "Kotlin", level = SkillLevel.HIGH),
      simpleSkill(id = 3, name = "Compose", level = SkillLevel.HIGH),
      simpleSkill(id = 4, name = "MVVM", level = SkillLevel.MEDIUM),
      simpleSkill(id = 5, name = "Multi Module", level = SkillLevel.MEDIUM),
      simpleSkill(id = 6, name = "Clean Architecture", level = SkillLevel.MEDIUM),
      simpleSkill(id = 7, name = "Coroutine", level = SkillLevel.MEDIUM),
      simpleSkill(id = 8, name = "Flow", level = SkillLevel.MEDIUM),
      simpleSkill(id = 9, name = "Git", level = SkillLevel.MEDIUM),
    ).sortedByDescending { it.level }
  }
}