package develop.shoppingmall.member.repository;

import develop.shoppingmall.member.service.dto.FindLoginMemberDTO;

interface MemberQueryRepository {

    FindLoginMemberDTO findPassword(String email);

    long deleteByEmail(String email);
}
