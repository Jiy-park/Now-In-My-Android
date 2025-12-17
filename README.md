# Now-In-My-Android
# 지금 나의 안드로이드는? [nowinandroid](https://github.com/android/nowinandroid) 컨셉의 개발 현황 프로젝트

# 사용 기술
`Android` | `Compose` | `Kotlin` | `Flow` | `Coroutine` | `MVVM` | `Hilt` | `Clean-Architecture` | `Coil` | `Build-logic`

# 프로젝트 구조
<img width="468" height="527" alt="project-dependency-graph" src="https://github.com/user-attachments/assets/1c7ea06b-2be9-4fde-b221-2f1355f55567" />

- `:screen` 모듈: 화면 단위 모듈. `:feat` 모듈을 참조할 수 있으며, `:screen` 모듈 간에는 참조하지 않음
- `:feat` 모듈: 기능 단위 모듈. 하나의 기능을 위해 `domain`, `data`, `presentation`의 세 레이어로 구성. `data` 레이어는 일반적으로 `internal`로 외부에 제공되지 않음. `:feat` 모듈 간에는 참조하지 않음
- `:core` 모듈: 앱에서 공용으로 사용되는 모듈.

# 추가 개발 발향
- `:core:network` 추가: 현재 데이터를 하드 코등하여 제공. 이를 서버(1차: `Supabase`, 2차: 자체 서버)로부터 받을 예정
- `Playgound` 화면 추가: 새롭게 개발했지만 하나의 앱 또는 화면으로 넣기엔 애매한 기능을 모아둔 화면 추가
- 본인 인증 및 데이터 조작 기능 추가: 본인 인증 기능을 추가한 후 인증된 인원만 데이터를 조작할 수 있는 기능 추가 예정
- 브랜치 별 다른 기술 스택 적용: 같은 기능을 하지만 다른 프레임워크, 라이브러리를 사용하는 경우 브랜치를 구분해 적용할 예정 (예로 네트워크 통신을 `Retrofit`으로 구현한 브랜치, `Ktor`로 구현한 브랜치 총 두 개의 브랜치로 구성)
- `KMP` 전환: 안드로이드 뿐 아니라 데스크탑, 웹, iOS에 배포 예정. (우선순위 낮음)

# 앱 영상
https://github.com/user-attachments/assets/bf2abef1-bac8-4ce1-9eed-1635e252d5e3

https://github.com/user-attachments/assets/3df778e0-725d-4940-8352-c5385b156f59

https://github.com/user-attachments/assets/8347cfc2-2c0e-4fa8-8243-7d489410f371

https://github.com/user-attachments/assets/8195ef5f-e56b-43b0-98f8-0a7fd8ffbbce
