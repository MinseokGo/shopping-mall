package develop.shoppingmall.member;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

interface MemberRepository extends JpaRepository<Member, Long>, MemberQueryRepository {

    boolean existsByEmail(String email);

    Optional<Member> findByEmail(String email);
}
