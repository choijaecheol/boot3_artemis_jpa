# 프로젝트 설정

이 프로젝트는 Spring Boot 3과 ActiveMQ Artemis를 사용하여 메시징을 처리합니다.

## 의존성 설정

- **Spring Boot Starter Artemis**: Spring Boot 3에서 ActiveMQ Artemis를 사용할 때 필요한 기본 의존성입니다.
- **ActiveMQ Artemis Jakarta EE 지원 버전**: ActiveMQ Artemis의 Jakarta EE 3.0을 지원하는 버전입니다.


아래와 같이 `README` 파일에 넣을 수 있는 형식으로 변환할 수 있습니다:

```markdown
# ActiveMQ Artemis를 사용한 메시지 송수신 예제

이 예제는 Spring Boot 3와 ActiveMQ Artemis를 사용하여 메시지를 송수신하는 간단한 예제입니다.

## 1. `build.gradle` 설정

ActiveMQ Artemis를 사용하려면 `build.gradle` 파일에 다음 의존성을 추가해야 합니다.

```gradle
// Spring Boot 3에서 ActiveMQ Artemis를 사용할 때
implementation 'org.springframework.boot:spring-boot-starter-artemis'

// ActiveMQ Artemis의 Jakarta EE 지원 버전
implementation 'org.apache.activemq:artemis-jakarta-server:2.28.0'
```

## 2. `application.properties` 설정

`application.properties`에서 ActiveMQ Artemis 설정을 추가합니다. 아래는 임베디드 브로커를 활성화하고, 데이터를 디스크에 저장하지 않도록 설정하는 예제입니다.

```properties
## activemq
spring.artemis.embedded.enabled=true
spring.artemis.embedded.persistent=false
```

## 3. 메시지 송수신 서비스 작성

### 3.1 `MessageProducer.java` (메시지를 브로커에 전송하는 프로듀서)

소스 위치: `com/example/demo/service/`

```java
package com.example.demo.service;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {

    private final JmsTemplate jmsTemplate;

    public MessageProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage(String destination, String message) {
        jmsTemplate.convertAndSend(destination, message);
    }
}
```

### 3.2 `MessageConsumer.java` (메시지를 수신하는 컨슈머)

소스 위치: `com/example/demo/service/`

```java
package com.example.demo.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {

    @JmsListener(destination = "test-queue")
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
    }
}
```

## 4. 테스트 컨트롤러 작성

### 4.1 `MessageController.java` (API를 통해 메시지를 전송하는 컨트롤러)

소스 위치: `com/example/demo/controller/`

```java
package com.example.demo.controller;

import com.example.demo.service.MessageProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private final MessageProducer messageProducer;

    public MessageController(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @GetMapping("/send")
    public String sendMessage(@RequestParam String message) {
        messageProducer.sendMessage("test-queue", message);
        return "Message sent: " + message;
    }
}
```

이 컨트롤러는 `/send` API를 통해 메시지를 전송할 수 있습니다. 예를 들어, `http://localhost:8080/send?message=Hello`와 같은 요청을 통해 메시지를 송신할 수 있습니다.

## 실행 방법

1. **의존성 설치**:
   `build.gradle`에서 의존성을 추가한 후, Gradle을 통해 프로젝트를 빌드합니다.

   ```bash
   ./gradlew build
   ```

2. **애플리케이션 실행**:
   아래 명령어로 Spring Boot 애플리케이션을 실행합니다.

   ```bash
   ./gradlew bootRun
   ```

3. **메시지 전송**:
   브라우저 또는 Postman에서 다음 URL을 통해 메시지를 전송합니다.

   ```
   http://localhost:8080/send?message=YourMessageHere
   ```

   메시지가 성공적으로 전송되면 `test-queue`에서 메시지를 수신할 수 있습니다.
```

이렇게 작성하면 `README` 파일에서 ActiveMQ Artemis를 사용하는 방법과 예제 코드들을 한눈에 볼 수 있습니다.

