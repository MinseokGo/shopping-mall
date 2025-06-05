package develop.auth.kafka.consumer;

import develop.auth.kafka.store.SignInResponseStore;
import develop.common.dto.SignInResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SignInConsumer {

    private final SignInResponseStore store;

    @KafkaListener(topics = "sign-in-response", groupId = "auth-service")
    public void consume(SignInResponseMessage message) {
        store.complete(message);
    }
}
