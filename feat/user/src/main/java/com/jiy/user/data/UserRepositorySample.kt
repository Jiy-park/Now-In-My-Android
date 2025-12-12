package com.jiy.user.data

import android.R.attr.name
import com.jiy.user.domain.UserRepository
import com.jiy.user.domain.model.Career
import com.jiy.user.domain.model.User
import com.jiy.user.domain.model.Skill
import kotlinx.coroutines.delay
import java.time.LocalDate

object UserRepositorySample: UserRepository {
  override suspend fun me(): User {
    delay(100)
    return User(
      id = 1,
      name = "박지용",
      phoneNum = "01039547706",
      email = "jiyong3954@gmail.com",
      profileImageUrl = "https://image.utoimage.com/preview/cp872722/2022/12/202212008462_500.jpg",
      shortIntroduction = "안녕하세요. 안드로이드 개발자 박지용입니다.",
    )
  }

  override suspend fun getMySkillStack(): List<Skill> {
    fun simpleSkill(id: Int, name: String) = Skill(id = id, name = name, iconUrl = null, level = null,  description = null)
    delay(100)
    return listOf(
      simpleSkill(id = 1,name = "Android"),
      simpleSkill(id = 2,name = "Kotlin"),
      simpleSkill(id = 3,name = "Compose"),
      simpleSkill(id = 4,name = "MVVM"),
      simpleSkill(id = 5,name = "Multi Module"),
      simpleSkill(id = 6,name = "Clean Architecture"),
      simpleSkill(id = 7,name = "Coroutine"),
      simpleSkill(id = 8,name = "Flow"),
      simpleSkill(id = 9,name = "Git"),
    )
  }

  override suspend fun getMyCareers(): List<Career> {
    delay(100)
    return listOf(
      Career(
        id = 1,
        companyName = "라임프렌즈",
        position = "안드로이드 개발자",
        startDate = LocalDate.of(2024, 2, 22),
        endDate = null,
        description = """
          ## 신규 프로젝트 설계 및 구현

          - 신규 프로젝트 설계 및 구현 주도
          - [기능 간단 구현 → 확인 → 리팩토링] 프로세스로 기능 품질 및 개발 효율 향상
          - Clean Architecture + Multi Module 구조 도입으로 신규 기능 추가 용이성 확보
          - build-logic 모듈로 프로젝트 Gradle 설정 일관성 유지
          - Fastlane + Firebase App Distribution, Google Play 내부 테스트 기반 테스트 환경 구축
          - 초기 세팅부터 배포까지 전체 개발 사이클 경험

          ## 프로젝트 리빌딩 작업 진행

          - 웹뷰 기반 앱을 안드로이드 네이티브 앱으로 전면 리빌딩하여 성능 및 안정성을 개선, 출시 후 신규 사용자 유입 및 이용량 대폭 증가
          - Jetpack Compose 기반으로 UI/애니메이션 구현 속도 향상, UX 개선
          - Kotlin Flow + Coroutine을 이용한 비동기 처리 안정성 확보 및 UI 반응성 개선
          - Clean Architecture + Multi Module 구조 도입으로 코드 가독성, 기능 확장성, 유지보수성 향상
          - Firebase Crashlytics 도입으로 문제 파악 속도, 대응성 향상
          - 클라이언트 미팅을 통한 요구사항 검증 및 개선 제안으로 제품 완성도 향상

          ## 템플릿 프로젝트 설계 및 구현

          - 신규 프로젝트 대응을 위한 공통 템플릿 설계 및 구현으로 초기 세팅 시간 대폭 단축
          - 템플릿 기반 개발을 통한 코드 일관성, 재사용성, 품질 향상
          - Clean Architecture + Multi Module 구조로 기능 추가 및 관리 용이성 확보
          - Jetpack Compose 기반 컴포넌트화로 UI 재사용성 및 생산성 증대
        """.trimIndent(),
      )
    )
  }
}