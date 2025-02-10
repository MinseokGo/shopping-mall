package develop.shoppingmall.member;

import org.springframework.data.jpa.repository.JpaRepository;

interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByEmail(String email);
}
