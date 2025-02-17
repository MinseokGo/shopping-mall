package develop.shoppingmall.member.repository;

import develop.shoppingmall.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberQueryRepository {

    boolean existsByEmail(String email);
}
