package gym.gateways;


import gym.entities.Member;
import gym.entities.Membership;

import java.util.List;

public interface MembershipGateway
{
    Membership save(Membership membership);

    List<Membership> findMembershipForMember(Member member);
}
