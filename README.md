![logo-1](exec/assets/logo-1.png)



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




안녕

안녕안녕
