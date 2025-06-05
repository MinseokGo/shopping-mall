package develop.member.query.infrastructure;

import develop.member.command.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberQueryRepository extends JpaRepository<Member, Long> {
}
