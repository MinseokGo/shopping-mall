package develop.shoppingmall.auth;

public final class JwtUtils {

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String SHOPPING_MALL_AUTH_SUBJECT = "Minseok Go";
    public static final String TOKEN_PROVIDER = "Minseok Go shopping mall server";
    public static final String TOKEN_SIGN_CLAIM = "email";

    public static final Long MILLISECONDS = 1000L;
    public static final Long SECONDS = 60L;
    public static final Long MINUTES = 60L;
    public static final Long HOURS = 24L;
    public static final int TOKEN_BEGIN_INDEX = 7;

    private JwtUtils() {
    }
}
