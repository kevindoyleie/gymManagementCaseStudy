package gym;


import java.util.List;

public interface MembershipGateway
{
    Membership save(Membership membership);

    List<Membership> findMembershipForMember(Member member);
}
