package develop.common.dto;

public record SignInRequestMessage(
        String requestId,
        String email,
        String password
) {
}
