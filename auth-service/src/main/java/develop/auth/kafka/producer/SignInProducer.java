package develop.auth.kafka.producer;

import develop.common.dto.SignInRequestMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SignInProducer {

    private final KafkaTemplate<String, SignInRequestMessage> kafkaTemplate;

    public void send(SignInRequestMessage message) {
        kafkaTemplate.send("sign-in-request", message.requestId(), message);
        log.info("로그인 요청 응답 완료 = {}", message);
    }
}
