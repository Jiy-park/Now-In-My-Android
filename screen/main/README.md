# :screen:main

메인 화면(홈 화면)을 담당하는 스크린 모듈입니다.

개발자의 프로필, 기술 스택, 최근 경력을 한눈에 보여주는 대시보드 형태의 화면을 제공합니다.

## 폴더 구조

### 루트 (`/`)
화면의 핵심 구성 요소를 포함합니다.

- **`MainScreen.kt`**: 메인 화면의 최상위 Composable
  - Scaffold 구조로 TopBar와 Content 구성
  - 사용자 정보, 기술 스택, 경력 목록을 섹션별로 표시
  - 로딩/성공/에러 상태에 따른 UI 처리
- **`MainViewModel.kt`**: 메인 화면의 비즈니스 로직 및 상태 관리
  - UserRepository, CareerRepository 통합
  - `Stateful<T>`를 사용한 상태 관리
- **`MainScreenNavigation.kt`**: 메인 화면으로의 네비게이션 정의

### `component/`
메인 화면에서 사용되는 UI 컴포넌트들을 포함합니다.

- **`MainScreenTopBar.kt`**: 메인 화면 전용 상단 앱바
- **`ContactBottomSheet.kt`**: 연락처 정보 표시 및 액션을 위한 BottomSheet

#### `component/user/`
사용자 정보 표시 컴포넌트입니다.

- **`UserComponent.kt`**: 사용자 프로필 카드 컴포넌트
  - 프로필 이미지, 이름, 소개
  - 전화, 이메일, GitHub 아이콘 버튼
- **`UserComponentPlaceholder.kt`**: 로딩 중 스켈레톤 UI

#### `component/skill/`
기술 스택 표시 컴포넌트입니다.

- **`SkillStackLabel.kt`**: "기술 스택" 섹션 제목
- **`SkillStackComponent.kt`**: 기술 목록 표시 컴포넌트
  - 기술별 아이콘, 이름, 숙련도 표시
- **`SkillStackComponentPlaceholder.kt`**: 로딩 중 스켈레톤 UI

#### `component/career/`
경력 정보 표시 컴포넌트입니다.

- **`CareersLabel.kt`**: "경력" 섹션 제목
- **`CareerListComponent.kt`**: 경력 목록 표시 컴포넌트
  - 최근 경력 3개를 미리보기로 표시
  - "더보기" 버튼으로 전체 경력 화면 이동

### `model/`
메�� 화면에서 사용되는 데이터 모델을 포함합니다.

- **`MainScreenNavEvent.kt`**: 메인 화면에서 발생하는 네비게이션 이벤트
  - `GitHub`: GitHub URL로 이동
  - `CareerDetail`: 경력 상세 화면으로 이동
  - `CareerList`: 전체 경력 목록 화면으로 이동
- **`ContactBottomSheetData.kt`**: 연락처 BottomSheet에 전달되는 데이터 모델

## 주요 기능

### 1. 사용자 프로필 표시
- 프로필 이미지, 이름, 간단한 소개 표시
- 전화, 이메일, GitHub 링크 제공
- 연락처 클릭 시 BottomSheet로 세부 정보 표시 및 액션

### 2. 기술 스택 표시
- 보유 기술 목록을 카드 형태로 표시
- 기술별 아이콘, 이름, 숙련도 레벨 표시
- 숙련도 순으로 정렬

### 3. 최근 경력 표시
- 최근 경력 3개를 미리보기로 표시
- 회사명, 포지션, 재직 기간, 사용 기술 표시
- 경력 클릭 시 상세 화면으로 이동
- "더보기" 버튼으로 전체 경력 목록 화면 이동

### 4. 상태 관리
- `Stateful<T>`를 활용한 로딩/성공/에러 상태 처리
- Crossfade 애니메이션으로 부드러운 상태 전환
- 각 섹션별 독립적인 로딩 상태 관리

## 의존성

- `:feat:user`: 사용자 정보 및 기술 스택 데이터
- `:feat:career`: 경력 정보 데이터
- `:core:ui`: 공통 UI 컴포넌트 및 테마
- `:core:core`: 상태 관리 (`Stateful<T>`)
- Hilt: ViewModel 의존성 주입
- Jetpack Compose Navigation: 화면 라우팅
