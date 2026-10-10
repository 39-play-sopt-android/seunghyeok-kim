# Play Sopt 39th 과제 Repository

해당 Repository는 play sopt 39th Android 과제에 대한 내용입니다.



## ⚙️ 프로젝트 구조

```agsl
spot/
├── app/                         ← 앱 진입점 (AAR)
│
├── build-logic/ 
│   ├── application                     ← 앱 진입점용 convention
│   ├── compose                         ← Compose 설정 convention
│   ├── feature                         ← feature 모듈 공통 convention
│   └── library                         ← 순수 라이브러리 convention
│
├── core/                               ← 공통 기반 모듈\
│   ├── designsystem                    ← 색상, 타이포, 컴포넌트 토큰
│   ├── navigation                      ← 앱 내 네비게이션 정의
│   └── ui                              ← 공용 UI 컴포넌트
│
├── feature/                            ← 화면 단위 기능 모듈
    └── auth                           ← login
```
