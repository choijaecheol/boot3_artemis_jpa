# 프로젝트 설정

이 프로젝트는 Spring Boot 3과 ActiveMQ Artemis를 사용하여 메시징을 처리합니다.

## 의존성 설정

- **Spring Boot Starter Artemis**: Spring Boot 3에서 ActiveMQ Artemis를 사용할 때 필요한 기본 의존성입니다.
- **ActiveMQ Artemis Jakarta EE 지원 버전**: ActiveMQ Artemis의 Jakarta EE 3.0을 지원하는 버전입니다.

### 사용된 의존성

```gradle
// Spring Boot 3에서 ActiveMQ Artemis를 사용할 때
implementation 'org.springframework.boot:spring-boot-starter-artemis'

// ActiveMQ Artemis의 Jakarta EE 지원 버전
implementation 'org.apache.activemq:artemis-jakarta-server:2.28.0'
