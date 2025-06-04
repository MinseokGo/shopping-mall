package develop.member.command.infrastructure;

import develop.member.command.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberCommandRepository extends JpaRepository<Member, Long> {

    boolean existsByEmail(String email);
}
