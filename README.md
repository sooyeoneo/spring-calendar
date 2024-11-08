# spring-calendar 🗓
## 일정 관리 앱 만들기
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

## ERD 📁
<img width="280" alt="스크린샷 2024-11-05 오후 5 38 32" src="https://github.com/user-attachments/assets/a29c205c-b91c-4bd3-8687-a80da71c2b5f">

## API 명세서 📋
[API 링크 바로가기](https://documenter.getpostman.com/view/39375040/2sAY4vfhKe)

------------
## 트러블 슈팅 🎯
[일정 관리 앱 만들기 ERD & API 바로가기](https://sooyeoneo.tistory.com/57)

[MySQL 내용 정리 바로가기](https://sooyeoneo.tistory.com/58)
