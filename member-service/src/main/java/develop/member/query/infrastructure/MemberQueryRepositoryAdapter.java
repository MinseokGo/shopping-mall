package develop.member.query.infrastructure;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import develop.common.domain.EntityStatus;
import develop.member.command.domain.QMember;
import develop.member.query.domain.MemberRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class MemberQueryRepositoryAdapter implements MemberRepository {

    private final QMember member;
    private final JPAQueryFactory queryFactory;
    private final MemberQueryRepository memberRepository;

    public MemberQueryRepositoryAdapter(JPAQueryFactory queryFactory, MemberQueryRepository memberRepository) {
        this.member = QMember.member;
        this.queryFactory = queryFactory;
        this.memberRepository = memberRepository;
    }

    @Override
    public String getPassword(String email) {
        return Optional.ofNullable(
                queryFactory
                        .select(member.password)
                        .from(member)
                        .where(isEqualEmailAndActive(email))
                        .fetchOne()
        ).orElseThrow();
    }

    public BooleanExpression isEqualEmailAndActive(String email) {
        return member.email.eq(email)
                .and(member.status.eq(EntityStatus.ACTIVE));
    }
}
