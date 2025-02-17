package develop.shoppingmall.member.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import develop.shoppingmall.member.service.dto.FindLoginMemberDTO;
import develop.shoppingmall.member.domain.QMember;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;

class MemberQueryRepositoryImpl implements MemberQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final QMember member;

    MemberQueryRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
        this.member = QMember.member;
    }

    @Override
    public FindLoginMemberDTO findPassword(String email) {
        return Optional.ofNullable(
                        jpaQueryFactory
                                .select(
                                        Projections.constructor(
                                                FindLoginMemberDTO.class,
                                                member.password
                                        )
                                )
                                .from(member)
                                .where(member.email.eq(email))
                                .fetchOne()
                )
                .orElseThrow(() -> new EntityNotFoundException("[ERROR] 멤버 정보를 찾을 수 없습니다."));
    }
}
