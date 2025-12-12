# Now-In-My-Android

지금 나의 안드로이드는? [nowinandroid](https://github.com/android/nowinandroid) 컨셉의 개발 현황 프로젝트

### 에러 코드

- 정의: 에러코드는 `UIException.code`에서 사용하는 코드로, 에러가 어디서 발생한건지 파악하기 위한 코드입니다.
- 형태: 에러코드는 `CC-U101` 형태로, 이는 모듈경로, 파일명, 파일내 구분자로 이루어집니다.
  - 모듈경로는 모듈의 첫글자를 따와 대문자로 작성합니다. 예를 들어 `CC-U101`의 `CC`는 `:core:core`를 의미합니다. 동일한 모듈명이 존재하는 경우 모듈명의 두번째 글자까지 사용하며, 이때 두번째 글자는 소문자로 작성합니다. 예를 들어 모듈명이 `:feat:user`, `:feat:usage` 이렇게 있는 경우 마지막으로 생성된 에러코드의 이름을 `FUs-xxx`형태로 작성합니다.
  - 파일명은 에러코드가 사용되는 파일의 이름을 단어 단위로 분리한 후 첫글자만 가져와 작성합니다. 예를 들어 `CC-U101`의 `U`는 `Util`를 의미합니다.
  - 파일내 구분자는 파일내에서 구분하기 위한 숫자로, 101부터 시작하여 작성합니다. 숫자에서 100의 자리는 함수를 구분하고, 10의 자리부터 1의자리까지 함수 내 위치를 구분합니다. 아래의 코드를 예시로 들 수 있습니다.
    ```kotlin
    // :feat:user / User.kt

    fun someFunction1() {
      ...
      if(condition1) {
        throw UIException(..., code = "FU-U101")
      }
      if(condition2) {
        throw UIException(..., code = "FU-U102")
      }
      ...
    }

    fun someFunction2() {
      when(...) {
        ... -> throw UIException(..., code = "FU-U201")
      }
    }
    ```