# Gabbit

## **Contributor**

| 최민영 | 권하림 |
|:----------|:----------|
| <img src="https://github.com/codingmy/server-self-study/assets/97686638/f79e5feb-a0b3-4e38-96b3-cc1a938d7b74" width="300" /> |  <img src="https://github.com/codingmy/server-self-study/assets/97686638/a8845c1f-e2e0-4741-891a-f0f45cf61260" width="300" /> |
|[@codingmy](https://github.com/codingmy, "codingmy")|[@KwonHalim](https://github.com/KwonHalim, "KwonHalim")
| 깃헙, Swagger, 컨벤션 초기 세팅| AWS 세팅 및 서버 배포 |
| ERD, 엔티티 설계 | ERD, 엔티티 설계|
| 회원가입 외 API | 회원가입 API |
| 리드미, API 명세서 작성 | 유저 자동 스케줄링 구현 |   
   

## **Architecture**
<img src="https://github.com/codingmy/server-self-study/assets/97686638/bf8ba5e6-3060-4335-8103-80816ab5bbd5"/>



## **ER Diagram**
<img src="https://github.com/codingmy/server-self-study/assets/97686638/ed962734-8e71-4c61-bc68-e40ac6d863d6/>

## **API Docs**

<img src="https://github.com/codingmy/server-self-study/assets/97686638/7218d2ad-25ba-406c-b686-0bebb85d3c5b/>


## **Directory**

<pre>
```
├─🗂️ main
│  ├─🗂️ java
│  │  └─🗂️ com
│  │          └─🗂️ MobileProgramming
│  │              ├─🗂️ controller
│  │              ├─🗂️ domain 
│  │              ├─🗂️ dto
│  │              │  └─🗂️ request 
│  │              │  └─🗂️ response
|  |              ├─🗂️ exception 
│  │              ├─🗂️ global
│  │              │  └─🗂️ config
│  │              │  └─🗂️ response
│  │              ├─🗂️ repository
│  │              └─🗂️ service
│  │              └─🗂️ serviceImpl 
│  └─🗂️ resources
│      ├─static
│      └─templates
└─🗂️ test
```
</pre>





## **Branch Convention**
- main
- feat/minyoung, feat/halim 각자 브랜치에서 작업 후 main에 머지, 이후 각자 브랜치에서 작업 반복
  
**커밋 예시** : [feat/#38] 유저 스케줄링 수정



## **Git 커밋 컨벤션**

*# feat : 기능 추가*

*# fix : 버그 수정*

*# docs : 문서 수정*

*# add : 파일, 의존성 추가*

*# refactor : 코드 리팩토링*

*# style : 코드 의미에 영향을 주지 않는 변경사항*

*# chore : 빌드 부분 혹은 패키지 매니저 수정사항*

## **Convention**
[협업 컨벤션 Notion](https://www.notion.so/codingmy/ebf60fc5d15743c9b6401881f04a477b?pvs=4, "협업 컨벤션 Notion")


## **Issue Number**

- **이슈 단위 커밋**으로 기능 개발을 관리합니다.
- GitHub에서 Issue Number를 커밋 메시지에 넣어줍니다.

