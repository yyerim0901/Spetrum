# <img src="exec/assets/logo-1.png" align="right" width="200px"/>🐾 Spetrum

### 반려동물과의 생활에 특화된 커뮤니티 서비스





## 🐾 목차

1. [개요](#개요)
2. [기술스택](#기술스택)
3. [화면정의서](#화면정의서)
4. [DB 설계](#DB-설계)
5. [API 설계](#API-설계)
6. [주요기능](#주요기능)
6. [참고](#참고)
7. [Git Convention](#Git-Convention)







## 🐾 개요

>### Member
>
>| Name   | Role     |
>| ------ | -------- |
>| 곽나현 | FrontEnd |
>| 김기도 | BackEnd  |
>| 김민철 | FrontEnd |
>| 김예림 | BackEnd  |
>| 서강준 | BackEnd  |
>
>
>
>### 개발 기간
>
>- 2021.10.11 - 2021.11.19
>
>
>
>### 기획 배경
>
>- 확대되는 반려동물 양육 가구 및 시장
>
>  ➜ `#펫스타그램` 등을 통한 반려동물과의 추억 저장소 및 공유 플랫폼 수요 급증
>
>  ➜ 여전히 높은 유기 동물, 미흡한 반려동물 양육 지식 
>
>
>
>### 차별점
>
>- 강아지와의 산책 추억 저장 및 공유
>- 반려동물의 종별 맞춤 서비스(강아지 ⇒ 도깅, 고양이 ⇒ 펫시터)
>- 기존의 일상sns과 펫sns의 중복 사용으로 인한 불편함을 해소
>- 유기동물 데이터를 이용해 동물의 성격을 기준으로 분류 후 데이터 사용
>- 펫피티아이 검사 결과를 기반으로 매칭되는 성격의 유기동물 추천 시스템
>- 채팅 기록을 redis에 저장하여 채팅 기록 유지
>
>
>
>### 요구사항 분석
>
>| Main | Detail |
>| :----------: | ------------------------------------------------------------ |
>| User       | 로그인, JWT, 로그아웃, 회원가입, 회원정보수정, 회원탈퇴, 팔로우, 팔로워, 유저 검색, 아이디 중복체크, 닉네임 중복체크 |
>| PetBTI     | 결과에 따른 보호동물 데이터 제공(공공데이터 기반)            |
>| PetSitter  | 사용자 위.경도 기반 위치정보 제공, 사용자 위치 기반 15km이내 가까운 순 으로 글 정렬, 사용자 간 채팅, 게시글 작성, 게시글 수정, 게시글 삭제, 게시글 목록, 게시글 상세 페이지, 나의 게시글 목록, 댓글 목록, 댓글 작성, 댓글 수정, 댓글 삭제 |
>| 냥댕<br>모먼트 | 나의 게시글 목록 (페이징), 다른 사람의 게시글 목록, 게시글 작성, 게시글 수정, 게시글 삭제, 댓글 목록, 댓글 작성 |
>| Dogging    | 도깅 기록(GPS데이터 기록), 도깅 기록 삭제, 도깅 상세 페이지 (기록된 경로 보여줌), 도깅 완료 후 사진 커스텀 |

[목차로 돌아가기](#목차)





## 🐾 기술스택

![기술스택](./exec/assets/기술스택.png)

[목차로 돌아가기](#목차)





## 🐾 화면정의서

### [Figma Link](https://www.figma.com/file/NZsbSCyV2PiCfj6HXEzvRm/%EC%8A%A4%ED%8E%AB%ED%8A%B8%EB%9F%BC?node-id=0%3A1)

[목차로 돌아가기](#목차)





## 🐾 DB 설계

### [ERDCloud Link](https://www.erdcloud.com/d/fg2a5Scm3Mzpy54FK)

![ERD](exec/assets/ERD.png)

[목차로 돌아가기](#목차)



## 🐾 API 설계

### [GitBook API Docs Link *수정 중*](https://app.gitbook.com/s/bZQoAX9kCH5UjHGZhcMt/reference/api-reference/undefined-1)

[목차로 돌아가기](#목차)



##  🐾 주요기능

### 회원

| 기능 | VIEW | NOTE |
| :----: | :----: | :----: |
| 회원가입 | <img src="./exec/assets/회원가입.png" width="50%"/><img src="./exec/assets/회원가입유효성검사.png" width="50%"/> | - 아이디 중복 검사<br>- 닉네임 중복 검사<br>- 프로필 사진 포함<br>- 유효성 검사 |
| 로그인 | <img src="./exec/assets/로그인.png" width="50%"/><img src="./exec/assets/로그인유효성검사.png" width="50%"/> | - ID/PW 유효성 검사 |
| 회원 정보 수정 | <img src="./exec/assets/회원정보수정.png"/> | |
| 회원 탈퇴 | <img src="./exec/assets/회원탈퇴.png"/> | - 모달창으로 한 번 더 확인 |
| 팔로우 하기 | <img src="./exec/assets/냥댕모먼트팔로우하기.png"/> | |
| 팔로우 끊기 | <img src="./exec/assets/냥댕모먼트팔로우끊기.png"/> | |
| 팔로우/팔로워 리스트 | <img src="./exec/assets/냥댕모먼트팔로우리스트.png"/> | |
| 마이페이지 | <img src="./exec/assets/마이페이지.png"/> | |

[목차로 돌아가기](#목차)



### 도깅

| 기능 | VIEW | NOTE |
| :----: | :----: | :----: |
| 도깅 기록 | <img src="./exec/assets/도깅진행페이지.png" width="50%"/><img src="./exec/assets/도깅결과페이지.png" width="50%"/> | - 도깅을 통해 이동한 거리를 Polygon으로 표시 |
| 도깅 사진 생성 | <img src="./exec/assets/도깅사진.png"/> | - 도깅을 한 날짜, 시간, 거리를 사진에 기록<br>- 스펫트럼 로고도 함께 기록 |

[목차로 돌아가기](#목차)



### 냥댕모먼트(Feed)

| 기능 | VIEW | NOTE |
| :----: | :----: | :----: |
| 내 글 보기 | <img src="./exec/assets/냥댕모먼트내글피드.png"/> |      |
| 팔로워 글 보기 | <img src="./exec/assets/냥댕모먼트팔로워피드.png"/> | |
| 게시글 추가, 수정 | <img src="./exec/assets/냥댕모먼트글작성.png"/> | |
| 게시글 조회,삭제 | <img src="./exec/assets/냥댕모먼트글상세페이지.png"/> | |
| 댓글 쓰기 | <img src="./exec/assets/냥댕모먼트댓글작성전.png" width="50%"/><img src="./exec/assets/냥댕모먼트댓글작성후.png" width="50%"/> | |
| 유저 검색 | <img src="./exec/assets/냥댕모먼트유저검색.png"/> | - 실시간 검색어 결과 보여줌 |

[목차로 돌아가기](#목차)



### 펫시터

| 기능 | VIEW | NOTE |
| :----: | :----: | :----: |
| 게시글 리스트 | <img src="./exec/assets/겟시터글목록.png"/> | - 글 거리순 정렬<br>(나에게 일정 거리 이내에서 작성된 글만 보여준다 / 거리에 따라 오름차순 정렬)<br>- Pagination 설정 |
| 현재 위치 | <img src="./exec/assets/겟시터현재위치.png"/> | - 글 쓰기 전 내 현재 위치 한글로 확인 가능 |
| 내 글 리스트 | <img src="./exec/assets/겟시터나의게시글목록.png"/> | - 자세히 보기 버튼을 통해 상세 페이지 이동 |
| 내 글 상세 페이지 | <img src="./exec/assets/겟시터나의게시글상세페이지.png"/> | - 내 글에서만 수정, 삭제 버튼을 통해 글 변경 가능 |
| 게시글 작성 (수정) | <img src="./exec/assets/겟시터글작성화면.png"/> | - 기본 글 양식 제공 |
| 글 상세 페이지 | <img src="./exec/assets/겟시터글상세페이지.png"/> | - 작성일자, 작성자 정보 제공 |
| 댓글 (작성, 수정, 삭제) | <img src="./exec/assets/겟시터댓글작성수정삭제.png"/> | - 댓글의 채팅 버튼을 통해 채팅 활성화 가능 |

[목차로 돌아가기](#목차)



### 펫피티아이(PetPTI)

| 기능 | VIEW | NOTE |
| :----: | :----: | :----: |
| 검사 진행 | <img src="./exec/assets/펫피티아이시작화면.png" width="50%"/><img src="./exec/assets/펫피티아이진행화면.png" width="50%"/> | - 공공데이터 크롤링 후 재조합하여 DB에 삽입 |
| 검사 결과 | <img src="./exec/assets/펫피티아이결과화면.png"/> | - 분류된 반려동물의 성격에 따라 결과 제공 |

[목차로 돌아가기](#목차)





## 참고

[유기동물 공공데이터](https://www.animal.go.kr/front/awtis/protection/protectionList.do?totalCount=6195&pageSize=10&menuNo=1000000060&&page=1)

[JPX Java library - *for creating, reading and writing GPS data in GPX format*](https://github.com/jenetics/jpx)

[Kakao Map API](https://apis.map.kakao.com/web/)



----

[목차로 돌아가기](#목차)





### :pushpin: Git Convention

#### Commit Rule

```
<[BE] or [FE]> <type> : <Commit 메세지> <(Jira 이슈번호)>
```

​	ex) `[FE] feat : user login 기능 개발(S05P21B101-0)`

##### 1. BE /FE

- BE : Backend와 관련된 코드 커밋
- FE : Frontend와 관련된 코드 커밋
- X (미기입) : 공통 수정사항 커밋(문서 수정 등)

##### 2. Type

- **feat:** A new feature
- **fix:** A bug fix
- **docs:** Changes to documentation
- **style:** Formatting, missing semi colons, etc; no code change
- **refactor:** Refactoring production code
- **test:** Adding tests, refactoring test; no production code change
- **chore:** Updating build tasks, package manager configs, etc; no production code change



#### Branch(Git-Flow)

**develop, master ** 브랜치에는 push 불가!

기능별 branch 생성후 작업 > 기능 완료후 develop에 merge하는 순서를 따른다.

##### 1. Branch 이름

```
feature/<FE or BE>/<function>
```

​	ex ) `feature/FE/userlogin`

##### 2. Merge 순서

 1. 로컬에서 작업

    - `git add ` 진행
    - `git commit` 진행
    - `git pull origin develop` 으로 develop최신코드와 충돌사항 수정
    - `git push origin <branch 이름>`

	2.  gitlab에서 작업

    **최신 develop코드 반영 완료된** branch를 merge request를 통해 develop과 merge!
프라이빗 푸시
