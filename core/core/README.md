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

#### 에러 코드 시스템

에러 코드는 `UIException.code`에서 사용하는 코드로, **에러가 어디서 발생했는지 추적하기 위한 식별자**입니다.

**형태**: `CC-U101` (모듈경로-파일명-구분자)

**구성 요소**:

1. **모듈 경로** (예: `CC`)
   - 모듈의 첫 글자를 대문자로 작성
   - 예: `CC` = `:core:core`, `FU` = `:feat:user`
   - 동일한 모듈명이 존재하는 경우 두 번째 글자까지 사용 (두 번째 글자는 소문자)
   - 예: `:feat:user` vs `:feat:usage` → 마지막 생성 모듈은 `FUs`로 표기

2. **파일명** (예: `U`)
   - 파일 이름을 단어 단위로 분리한 후 각 단어의 첫 글자를 대문자로 작성
   - 예: `Util.kt` → `U`, `UserRepository.kt` → `UR`

3. **파일 내 구분자** (예: `101`)
   - 101부터 시작하는 3자리 숫자
   - 100의 자리: 함수 구분 (1, 2, 3...)
   - 10의 자리 + 1의 자리: 함수 내 위치 구분 (01, 02, 03...)

**예시**:

```kotlin
// :feat:user / User.kt

fun someFunction1() {
  ...
  if(condition1) {
    throw UIException(..., code = "FU-U101")  // 첫 번째 함수의 첫 번째 에러
  }
  if(condition2) {
    throw UIException(..., code = "FU-U102")  // 첫 번째 함수의 두 번째 에러
  }
  ...
}

fun someFunction2() {
  when(...) {
    ... -> throw UIException(..., code = "FU-U201")  // 두 번째 함수의 첫 번째 에러
  }
}
```

### `model/`
애플리케이션 전역에서 사용되는 공통 데이터 모델을 포함합니다.

### `util/`
애플리케이션 전역에서 사용되는 유틸리티 함수들을 포함합니다. 
