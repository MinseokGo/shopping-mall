package develop.auth.kafka.store;

import develop.common.dto.SignInResponseMessage;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class SignInResponseStore {

    private final Map<String, CompletableFuture<SignInResponseMessage>> store = new ConcurrentHashMap<>();

    public CompletableFuture<SignInResponseMessage> createFuture(String requestId) {
        CompletableFuture<SignInResponseMessage> future = new CompletableFuture<>();
        store.put(requestId, future);
        return future;
    }

    public void complete(SignInResponseMessage message) {
        CompletableFuture<SignInResponseMessage> future = store.remove(message.requestId());
        if (Objects.nonNull(future)) {
            future.complete(message);
        }
    }
}
