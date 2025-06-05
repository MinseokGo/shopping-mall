package develop.member.query.dto;

public record SignInRequestMessage(
        String requestId,
        String email,
        String password
) {
}
