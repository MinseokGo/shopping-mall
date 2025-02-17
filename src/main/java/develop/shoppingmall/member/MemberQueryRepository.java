package develop.shoppingmall.member;

interface MemberQueryRepository {

    FindLoginMemberDTO findPassword(String email);
}
