package develop.auth.application;

import develop.auth.domain.AuthTokenProvider;
import develop.auth.dto.SignInRequest;
import develop.auth.exception.LoginFailedException;
import develop.auth.kafka.producer.SignInProducer;
import develop.auth.kafka.store.SignInResponseStore;
import develop.common.dto.SignInRequestMessage;
import develop.common.dto.SignInResponseMessage;
import develop.common.exception.ErrorCode;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignInService {

    private final SignInProducer producer;
    private final SignInResponseStore store;
    private final AuthTokenProvider tokenProvider;

    public String signIn(SignInRequest request) {
        String requestId = UUID.randomUUID().toString();
        CompletableFuture<SignInResponseMessage> future = store.createFuture(requestId);

        SignInRequestMessage requestMessage = new SignInRequestMessage(requestId, request.email(), request.password());
        producer.send(requestMessage);

        try {
            develop.common.dto.SignInResponseMessage responseMessage = future.get(3, TimeUnit.SECONDS);
            if (responseMessage.success()) {
                return tokenProvider.createToken(request.email());
            }
            throw new LoginFailedException();
        } catch (TimeoutException e) {
            throw new LoginFailedException(ErrorCode.LOGIN_TIMEOUT);
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
            throw new LoginFailedException();
        }
    }
}
