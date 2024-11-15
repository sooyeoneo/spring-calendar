# spring-calendar 🗓
## 일정 관리 앱 서버 만들기 🧑🏻‍💻
### Lv 0. API 명세 및 ERD, SQL 작성   `필수`

 - [X]  **API 명세서 작성하기**
    - [X]  API명세서는 프로젝트 root(최상위) 경로의 `README.md` 에 작성
    - 참고) API 명세서 작성 가이드
        - API 명세서란 API명, 요청 값(파라미터), 반환 값, 인증/인가 방식, 데이터 및 전달 형식 등 API를 정확하게 호출하고 그 결과를 명확하게 해석하는데 필요한 정보들을 일관된 형식으로 기술한 것을 의미한다.
        - request 및 response는 [json(링크)](https://namu.wiki/w/JSON) 형태로 작성한다.
     
        
        > API 명세서 추천 무료 Tool
        > 
        > 
        > [Document your APIs in Postman | Postman Learning Center](https://learning.postman.com/docs/publishing-your-api/api-documentation-overview/)
        >
 - [X]  **ERD 작성하기**
    - [X]  ERD는 프로젝트 root(최상위) 경로의 `README.md` 에 첨부

          
  - API 명세 작성을 통해 서비스의 큰 흐름과 기능을 파악하고 기능을 구현하기 위해 필요한 데이터가 무엇인지 생각해보기.
    - 이때, 구현해야 할 서비스의 영역별로 필요한 데이터를 설계하고 각 영역간의 관계를 표현하는 방법을 ERD(Entity Relationship Diagram)라고 한다.
- ERD의 의미
    - E(Entity. 개체)
        - 구현 할 서비스의 영역에서 필요로 하는 데이터를 담을 개체를 의미
            - ex) `책`, `저자`, `독자`, `리뷰`
    - A(Attribute. 속성)
        - 각 개체가 가지는 속성을 의미
            - ex) 책은 `제목`, `언어`, `출판일`, `저자`, `가격` 등의 속성을 가질 수 있다.
    - R(Relationship. 관계)
        - 개체들 사이의 관계를 정의
            - ex) `저자`는 여러 권의 `책`을 집필할 수 있습니다. 이때, 저자와 책의 관계는 일대다(1:N) 관계
 - [X]  **SQL 작성하기**
    - [X]  설치한 데이터베이스(Mysql)에 ERD를 따라 테이블을 생성
    - [X]  필수 기능 가이드 개발에 필요한 테이블을 생성하는 query를 작성
        - [X]  `Create`
    - [X]  일정 생성을 하는 query를 작성
        - [X]  `Insert`
    - [X]  전체 일정을 조회하는 query를 작성
        - [X]  `Select`
    - [X]  선택 일정을 조회하는 query를 작성
        - [X]  `Select`
    - [X]  선택한 일정을 수정하는 query를 작성
        - [X]  `Update`
    - [X]  선택한 일정을 삭제하는 query를 작성
        - [X]  `Delete`
    - 참고) SQL 작성 가이드
        - 과제 프로그램의 root(최상위) 경로에`schedule.sql` 파일을 만들고 query를 작성
     
---------------
## 기능 구현 🖥
### 개발 전, 공통 조건

- 일정 작성, 수정, 조회 시 반환 받은 일정 정보에 `비밀번호`는 제외
- 일정 수정, 삭제 시 선택한 일정의 `비밀번호`와 요청할 때 함께 보낸 `비밀번호`가 일치할 경우에만 가능
    - 비밀번호가 일치하지 않을 경우 적절한 오류 코드 및 메세지를 반환해야 한다.
- 일정 생성, 수정, 삭제 시 적절한 상태코드를 반환해야 한다.
    - 참고: [`HTTP 상태 코드(링크)`](https://developer.mozilla.org/ko/docs/Web/HTTP/Status)
- `3 Layer Architecture` 에 따라 각 Layer의 목적에 맞게 개발해야 한다.
- CRUD 필수 기능은 모두 데이터베이스 연결 및 `JDBC` 를 사용해서 개발해야 한다.
    - JDBC 설정은 강의와 강의에서 제공해주는 코드 스니펫을 참조
    - ‼ JPA가 아닌 JDBC로 하는 이유?
        - 데이터베이스와의 연동을 위해 JDBC를 사용해보며, 기본적인 SQL 쿼리 작성과 데이터 관리를 연습
        - 충분히 익숙해지고 난 후, JPA를 도입할 예정.
     
### Lv 1. 일정 생성 및 조회  `필수`

 - [X]  **일정 생성(일정 작성하기)**
    - [X]  일정 생성 시, 포함되어야할 데이터
        - [X]  `할일`, `작성자명`, `비밀번호`, `작성/수정일`을 저장
        - [X]  `작성/수정일`은 날짜와 시간을 모두 포함한 형태
    - [X]  각 일정의 고유 식별자(ID)를 자동으로 생성하여 관리
    - [X]  최초 입력 시, 수정일은 작성일과 동일
 - [X]  **전체 일정 조회(등록된 일정 불러오기)**
    - [X]  다음 조건을 바탕으로 등록된 일정 목록을 전부 조회
        - [X]  `수정일` (형식 : YYYY-MM-DD)
        - [X]  `작성자명`
    - [X]  조건 중 한 가지만을 충족하거나, 둘 다 충족을 하지 않을 수도, 두 가지를 모두 충족할 수도 있다.
    - [X]  `수정일` 기준 내림차순으로 정렬하여 조회
 - [X]  **선택 일정 조회(선택한 일정 정보 불러오기)**
    - [X]  선택한 일정 단건의 정보를 조회할 수 있다.
    - [X]  일정의 고유 식별자(ID)를 사용하여 조회

### Lv 2. 일정 수정 및 삭제  `필수`

- [X]  **선택한 일정 수정**
    - [X]  선택한 일정 내용 중 `할일`, `작성자명` 만 수정 가능
        - [X]  서버에 일정 수정을 요청할 때 `비밀번호`를 함께 전달
        - [X]  `작성일` 은 변경할 수 없으며, `수정일` 은 수정 완료 시, 수정한 시점으로 변경
- [X]  **선택한 일정 삭제**
    - [X]  선택한 일정을 삭제할 수 있다.
        - [X]  서버에 일정 수정을 요청할 때 `비밀번호`를 함께 전달

------------
## ERD 📁
<img width="280" alt="최종 ERD에 bigint 수정  2024-11-06 오전 11 25 28" src="https://github.com/user-attachments/assets/18388e80-f58e-47d4-9b70-3940c46103bb">

------------
## API 명세서 📋
[Postman_spring-calendar API 명세서](https://documenter.getpostman.com/view/39375040/2sAY4vfhKe)

------------
## 트러블 슈팅 🎯
[일정 관리 앱 만들기 ERD & API](https://sooyeoneo.tistory.com/57)

[MySQL 내용 정리](https://sooyeoneo.tistory.com/58)

[일정 관리 앱 서버 만들기_트러블슈팅.TIL](https://sooyeoneo.tistory.com/62)