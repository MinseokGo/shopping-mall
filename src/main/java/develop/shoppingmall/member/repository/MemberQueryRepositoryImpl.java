package develop.shoppingmall.member.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import develop.shoppingmall.member.domain.MemberStatus;
import develop.shoppingmall.member.domain.QMember;
import develop.shoppingmall.member.exception.MemberNotFoundException;
import develop.shoppingmall.member.service.dto.FindLoginMemberDTO;
import java.util.Optional;

class MemberQueryRepositoryImpl implements MemberQueryRepository {

    private final QMember member;
    private final JPAQueryFactory jpaQueryFactory;

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
                                .where(isEqualEmail(email))
                                .where(isValidMemberStatus())
                                .fetchOne()
                )
                .orElseThrow(MemberNotFoundException::new);
    }

    private BooleanExpression isEqualEmail(String email) {
        return member.email.eq(email);
    }

    private BooleanExpression isValidMemberStatus() {
        return member.status.eq(MemberStatus.ACTIVE);
    }
}
