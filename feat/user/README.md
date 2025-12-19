# :feat:user

사용자(개발자) 정보 관리를 담당하는 기능 모듈입니다.

사용자 프로필, 기술 스택, 연락처 정보 등 개발자의 기본 정보와 관련된 비즈니스 로직을 포함합니다.

## 폴더 구조

### `domain/`
도메인 레이어로, 비즈니스 로직의 핵심을 담당합니다.

#### `domain/model/`
사용자 관련 도메인 모델을 정의합니다.

- **`User.kt`**: 사용자 기본 정보 모델
  - 이름, 생년월일, 연락처 (전화번호, 이메일)
  - GitHub URL, 프로필 이미지
  - 간단한 자기소개
- **`Skill.kt`**: 기술 스택 정보 모델
  - 기술명, 숙련도, 관련 아이콘 등
- **`SkillLevel.kt`**: 기술 숙련도 레벨 정의 (예: Beginner, Intermediate, Expert)

#### `domain/`
- **`UserRepository.kt`**: 사용자 데이터 접근을 위한 Repository 인터페이스

### `data/`
데이터 레이어로, Repository 구현체와 데이터 소스를 포함합니다.

- **`UserRepositorySample.kt`**: 샘플/더미 데이터를 제공하는 Repository 구현체
- **`UserDataModule.kt`**: Hilt 의존성 주입을 위한 모듈 정의

### `presentation/`
재사용 가능한 UI 컴포넌트와 프레젠테이션 로직을 포함합니다.

- **`ProfileImage.kt`**: 사용자 프로필 이미지 표시 컴포넌트
- **`Dummies.kt`**: 개발/테스트용 더미 데이터

## 아키텍처

이 모듈은 **Clean Architecture** 원칙을 따릅니다:
- **Domain Layer**: 비즈니스 로직과 모델 정의 (안드로이드 의존성 없음)
- **Data Layer**: Repository 구현, 데이터 소스 관리
- **Presentation Layer**: 재사용 가능한 UI 컴포넌트

## 의존성

- `:core:core`: 핵심 상태 관리 및 예외 처리
- `:core:ui`: 공통 UI 컴포넌트
- Hilt: 의존성 주입
- Kotlinx Serialization: 데이터 직렬화
- Jetpack Compose: UI 프레임워크
