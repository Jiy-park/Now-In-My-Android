# :core:ui

UI 관련 공통 컴포넌트, 테마, 유틸리티를 제공하는 모듈입니다.

Jetpack Compose 기반의 UI 요소들과 디자인 시스템을 포함하고 있으며, 모든 화면 모듈에서 재사용 가능합니다.

## 폴더 구조

### `theme/`
애플리케이션 전역 디자인 시스템을 정의합니다.

- **`Color.kt`**: 애플리케이션에서 사용되는 색상 팔레트
- **`Typo.kt`**: 타이포그래피 스타일 정의
- **`Spacing.kt`**: 일관된 간격 시스템 (마진, 패딩)
- **`Padding.kt`**: 컴포넌트별 패딩 값 정의
- **`Shape.kt`**: 컴포넌트 모서리 둥글기 등 도형 스타일

### `component/`
재사용 가능한 Compose UI 컴포넌트들을 포함합니다.

- **`TopBar.kt`**: 공통 상단 앱바 컴포넌트
- **`Indicator.kt`**: 로딩 인디케이터, 진행 상태 표시 컴포넌트
- **`Label.kt`**: 라벨, 태그 등의 텍스트 표시 컴포넌트

### `modifier/`
Compose Modifier 확장 함수들을 포함합니다.

- **`Clickable.kt`**: 커스텀 클릭 이벤트 처리를 위한 Modifier 확장
- **`Shimmer.kt`**: 로딩 시 스켈레톤 UI를 위한 Shimmer 효과 Modifier

### `state/`
UI 상태 관리와 관련된 유틸리티를 포함합니다.

- **`StatefulEvent.kt`**: UI 이벤트 상태 관리를 위한 클래스

### `navigation/`
네비게이션 관련 유틸리티를 포함합니다.

- **`ScreenRoute.kt`**: 화면 라우팅을 위한 타입 안전 네비게이션 경로 정의

### `util/`
UI 관련 유틸리티 함수들을 포함합니다.

- **`DateTimeUtil.kt`**: 날짜/시간 포맷팅 및 변환 유틸리티

## 의존성

- `androidx.compose.*`: Jetpack Compose UI 프레임워크
- `com.valentinilk.shimmer:compose-shimmer`: Shimmer 효과 라이브러리
- `:core:core`: 핵심 상태 관리 및 예외 처리
