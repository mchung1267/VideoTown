# VideoTown - Readme

# 프로젝트 소개

VideoTown 은 Spring Boot를 사용하여 유명 동영상 스트리밍 플랫폼 YouTube를 참고하여 만든 동영상 재생 플랫폼을 위한 정산 시스템 입니다.

# 개발 기간

1. 11. 15 ~ 2024. 11. 12. (4주) + 지속

# 개발 환경

- Java 21
- Spring Boot 3.3.4
- MySQL 9.0.1

# 주요 기능

### 회원 가입 및 로그인, 로그아웃 기능

### 동영상 관련 기능

- 동영상 추가
    - 제목, 길이, 게시자 정보 저장
    - 동영상 길이에 따라 자동으로 광고 추가 (5분이상 10분미만 영상에 15초 광고 추가, 10분 이상 영상에 광고 2개 추가)
- 동영상 시청
    - 영상 시청 시 조회수 집계 기능
    - 광고 시청 시 광고를 시청한 것으로 처리 및 광고 시청 기록에 집계

### 정산 관련 기능

- 스케쥴러를 통해 광고 시청 및 동영상 시청 정보를 각 유저별로 정리
    - 매일 스케쥴러를 통해 동영상 및 광고 시청 정보를 정리하여 수익금 정산 데이터베이스에 기록
    - 각 데이터를 요약하여 일일 총 수익금 기록으로도 (별도의 테이블에) 저장
- 정산 데이터 조회 기능
    - 1일, 1주, 1개월 단위를 선택하여 수익금 정산기록 조회 가능
    - 각 정산 리포트에는 각 컨텐츠별 수익금 정보 역시 함께 포함

# MSA

MSA 구조를 사용하여, 서비스를 성공적으로 분리 하였습니다.

## 서비스 구조

- Eureka Server
    - Eureka Server을 통해 MSA의 기초를 구현하였습니다.
- Eureka Gateway
    - 게이트웨이에 Spring Security 필터 등을 적용하여, 로그인하지 않을 경우 각 서비스로의 접근을 차단 하였습니다.
- PayoutService
    - 정산 데이터와 관련된 서비스를 따로 분리하였습니다.
- VideoService
    - 동영상 업로드, 시청 등 기본적인 동영상 및 광고 관련 CRUD에 관한 기능을 수행합니다
- AuthService
    - 회원 가입, 로그인, 로그아웃 및 JWT토큰 발행을 담당하는 서비스입니다.
