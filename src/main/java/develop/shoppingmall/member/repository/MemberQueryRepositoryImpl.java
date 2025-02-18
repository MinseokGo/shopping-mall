package develop.shoppingmall.member.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import develop.shoppingmall.member.domain.MemberStatus;
import develop.shoppingmall.member.domain.QMember;
import develop.shoppingmall.member.exception.MemberNotFoundException;
import develop.shoppingmall.member.service.dto.FindLoginMemberDTO;
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
                                .where(member.status.eq(MemberStatus.ACTIVE))
                                .fetchOne()
                )
                .orElseThrow(MemberNotFoundException::new);
    }
}
