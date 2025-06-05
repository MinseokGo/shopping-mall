package develop.common.dto;

public record SignInResponseMessage(
        String requestId,
        boolean success
) {
}
