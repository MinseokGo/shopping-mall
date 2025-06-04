package develop.member.command.domain;

public interface MemberRepository {

    boolean existsByEmail(String email);
    void save(Member member);
}
