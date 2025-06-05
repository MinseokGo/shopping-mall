package develop.member.query.infrastructure.kafka.consumer;

import develop.common.dto.SignInRequestMessage;
import develop.common.dto.SignInResponseMessage;
import develop.member.query.application.MemberQueryService;
import develop.member.query.infrastructure.kafka.producer.SignInResponseProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SignInRequestConsumer {

    private final MemberQueryService memberService;
    private final SignInResponseProducer producer;

    @KafkaListener(topics = "sign-in-request", groupId = "member-service")
    public void handle(ConsumerRecord<String, SignInRequestMessage> record) {
        SignInRequestMessage message = record.value();
        log.info("로그인 요청 수신 = {}", message);

        SignInResponseMessage response = memberService.isPossibleLogin(message);
        producer.response(response);
    }
}
