# :feat:career

경력 정보 관리를 담당하는 기능 모듈입니다.

회사 경력, 프로젝트 경험, 사용 기술 등 개발자의 커리어와 관련된 비즈니스 로직과 UI 컴포넌트를 포함합니다.

## 폴더 구조

### `domain/`

도메인 레이어로, 경력 관련 비즈니스 로직의 핵심을 담당합니다.

#### `domain/model/`

경력 관련 도메인 모델을 정의합니다.

- **`CareerListItem.kt`**: 경력 목록 항목 모델
  - 회사명, 회사 이미지, 포지션
  - 입사일, 퇴사일 (재직 중인 경우 null)
  - 사용 기술 목록
- **`CareerDetail.kt`**: 경력 상세 정보 모델
  - 경력 기본 정보 + 상세 설명
  - 프로젝트 상세, 담당 업무 등
- **`CareerSkill.kt`**: 경력에서 사용한 기술 정보 모델
- **`CareerListOption.kt`**: 경력 목록 조회 옵션 (필터, 정렬 등)

#### `domain/`

- **`CareerRepository.kt`**: 경력 데이터 접근을 위한 Repository 인터페이스

### `data/`

데이터 레이어로, Repository 구현체와 데이터 소스를 포함합니다.

- **`CareerRepositorySample.kt`**: 샘플/더미 데이터를 제공하는 Repository 구현체
- **`CareerDataModule.kt`**: Hilt 의존성 주입을 위한 모듈 정의

### `presentation/`

재사용 가능한 경력 관련 UI 컴포넌트를 포함합니다.

#### `presentation/component/`

경력 표시를 위한 Compose 컴포넌트들입니다.

- **`CareerListItemComponent.kt`**: 경력 목록 항목 UI 컴포넌트
- **`CareerListComponentPlaceholder.kt`**: 로딩 중 표시되는 스켈레톤 UI
- **`CompanyImage.kt`**: 회사 로고/이미지 표시 컴포넌트

#### `presentation/dummy/`

- **`CareerDummy.kt`**: 개발/테스트용 더미 데이터

## 아키텍처

이 모듈은 **Clean Architecture** 원칙을 따릅니다:

- **Domain Layer**: 비즈니스 로직과 모델 정의
- **Data Layer**: Repository 구현, 데이터 소스 관리
- **Presentation Layer**: 재사용 가능한 UI 컴포넌트 (다른 screen 모듈에서 사용)

## 특징

- **재사용 가능한 컴포넌트**: `CareerListItemComponent` 등은 여러 화면에서 재사용됩니다
- **스켈레톤 UI**: 로딩 상태를 위한 Placeholder 컴포넌트 제공
- **타입 안전성**: LocalDate를 사용한 날짜 처리

## 의존성

- `:core:core`: 핵심 상태 관리 및 예외 처리
- `:core:ui`: 공통 UI 컴포넌트
- Hilt: 의존성 주입
- Kotlinx Serialization: 데이터 직렬화
- Jetpack Compose: UI 프레임워크
