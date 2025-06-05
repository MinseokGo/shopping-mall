package develop.member.query.dto;

public record SignInResponseMessage(
        String requestId,
        boolean success
) {
}
