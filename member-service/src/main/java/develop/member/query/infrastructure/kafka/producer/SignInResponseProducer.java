package develop.member.query.infrastructure.kafka.producer;

import develop.common.dto.SignInResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SignInResponseProducer {

    private final KafkaTemplate<String, SignInResponseMessage> kafkaTemplate;

    public void response(SignInResponseMessage message) {
        kafkaTemplate.send("sign-in-response", message);
    }
}
