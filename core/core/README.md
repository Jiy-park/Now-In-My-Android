# :core:core

앱의 코어 모듈로 전역적으로 사용하는 모델, 유틸 등이 집합한 모듈입니다.

모든 모듈에서 사용되어야 하는 만큼 안드로이드 종속이 없는 순수 `kotlin` 모듈입니다.

## 폴더 구조

### `state/`
애플리케이션 상태 관리를 위한 핵심 컴포넌트들을 포함합니다.

- **`_Stateful.kt`**: `Stateful<T>` sealed interface 정의
  - `Loading`: 데이터 로딩 중 상태
  - `Success<T>`: 데이터를 성공적으로 가져온 상태
  - `Error`: 에러 발생 상태
- **`Builder.kt`**: Stateful 객체 생성을 위한 빌더 함수들
- **`MapState.kt`**: Stateful 상태 변환을 위한 매핑 함수들
- **`OnState.kt`**: Stateful 상태에 따른 분기 처리 함수들
- **`Util.kt`**: 상태 관리 관련 유틸리티 함수들

### `exception/`
애플리케이션 전역 예외 처리를 위한 클래스들을 포함합니다.

- **`UIException.kt`**: 사용자에게 노출되는 커스텀 예외 클래스
  - `mainMessage`: 예외의 주요 메시지
  - `subMessage`: 예외의 부가 메시지 (선택)
  - `code`: 예외 추적을 위한 에러 코드 (선택)

### `model/`
애플리케이션 전역에서 사용되는 공통 데이터 모델을 포함합니다.

### `util/`
애플리케이션 전역에서 사용되는 유틸리티 함수들을 포함합니다. 
