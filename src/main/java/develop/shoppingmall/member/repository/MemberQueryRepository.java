package develop.shoppingmall.member.repository;

import develop.shoppingmall.member.FindLoginMemberDTO;

interface MemberQueryRepository {

    FindLoginMemberDTO findPassword(String email);
}
