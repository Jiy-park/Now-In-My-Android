# :screen:career

경력 관련 화면들을 담당하는 스크린 모듈입니다.

경력 목록 조회 및 개별 경력의 상세 정보를 표시하는 두 개의 화면을 포함합니다.

## 폴더 구조

### `list/`
전체 경력 목록을 표시하는 화면입니다.

#### `list/` 루트
- **`CareerListScreen.kt`**: 경력 목록 화면의 최상위 Composable
  - 전체 경력 목록을 LazyColumn으로 표시
  - TopBar에 사용자 프로필 이미지 표시
  - 경력 항목 클릭 시 상세 화면으로 이동
- **`CareerListViewModel.kt`**: 경력 목록 화면의 비즈니스 로직 및 상태 관리
  - CareerRepository를 통한 경력 목록 조회
  - UserRepository를 통한 사용자 정보 조회
- **`CareerListScreenNavigation.kt`**: 경력 목록 화면으로의 네비게이션 정의

#### `list/component/`
경력 목록 화면 전용 컴포넌트입니다.

- **`UserBottomSheet.kt`**: 사용자 정보를 표시하는 BottomSheet
  - TopBar의 프로필 이미지 클릭 시 표시
  - 사용자 상세 정보 표시

### `detail/`
개별 경력의 상세 정보를 표시하는 화면입니다.

#### `detail/` 루트
- **`CareerDetailScreen.kt`**: 경력 상세 화면의 최상위 Composable
  - 회사 정보, 포지션, 재직 기간 표시
  - 사용 기술 스택 표시
  - 경력 상세 설명 (프로젝트, 업무 내용 등) 표시
- **`CareerDetailViewModel.kt`**: 경력 상세 화면의 비즈니스 로직 및 상태 관리
  - CareerRepository를 통한 경력 상세 정보 조회
- **`CareerDetailScreenNavigation.kt`**: 경력 상세 화면으로의 네비게이션 정의

#### `detail/component/`
경력 상세 화면 전용 컴포넌트입니다.

- **`CompanyComponent.kt`**: 회사 정보 표시 컴포넌트
  - 회사 로고, 이름, 포지션, 재직 기간 표시
- **`CareerSkillStack.kt`**: 해당 경력에서 사용한 기술 스택 표시 컴포넌트

#### `detail/content/`
경력 상세 화면의 콘텐츠 컴포넌트입니다.

- **`CareerDetailLoadingContent.kt`**: 로딩 중 표시되는 스켈레톤 UI

## 화면 구조

### 1. 경력 목록 화면 (`CareerListScreen`)
**목적**: 전체 경력 이력을 시간 순으로 표시

**주요 기능**:
- 전체 경력 목록을 카드 형태로 표시
- 각 카드에 회사명, 포지션, 재직 기간, 사용 기술 표시
- TopBar에 사용자 프로필 이미지 표시
- 프로필 이미지 클릭 시 사용자 정보 BottomSheet 표시
- 경력 카드 클릭 시 상세 화면으로 이동

**상태 관리**:
- `userState: Stateful<User>` - 사용자 정보
- `careerListState: Stateful<List<CareerListItem>>` - 경력 목록

### 2. 경력 상세 화면 (`CareerDetailScreen`)
**목적**: 선택한 경력의 상세 정보를 표시

**주요 기능**:
- 회사 로고 및 기본 정보 표시
- 포지션 및 재직 기간 표시
- 해당 경력에서 사용한 기술 스택 표시
- 경력 상세 설명 (프로젝트, 담당 업무 등) 표시
- 로딩 상태에 대한 스켈레톤 UI 제공

**상태 관리**:
- `careerDetailState: Stateful<CareerDetail>` - 경력 상세 정보

## 화면 간 네비게이션

```
MainScreen (최근 경력 3개 미리보기)
    ↓ "더보기" 버튼 클릭
CareerListScreen (전체 경력 목록)
    ↓ 경력 카드 클릭
CareerDetailScreen (경력 상세)
```

## 의존성

- `:feat:career`: 경력 데이터 및 재사용 컴포넌트
- `:feat:user`: 사용자 정보 데이터
- `:core:ui`: 공통 UI 컴포넌트 (TopBar, Label 등)
- `:core:core`: 상태 관리 (`Stateful<T>`)
- Hilt: ViewModel 의존성 주입
- Jetpack Compose Navigation: 화면 라우팅
