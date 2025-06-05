package develop.member.command.infrastructure;

import develop.member.command.domain.Member;
import develop.member.command.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberCommandRepositoryAdapter implements MemberRepository {

    private final MemberCommandRepository memberRepository;

    @Override
    public boolean existsByEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

    @Override
    public void save(Member member) {
        memberRepository.save(member);
    }
}
