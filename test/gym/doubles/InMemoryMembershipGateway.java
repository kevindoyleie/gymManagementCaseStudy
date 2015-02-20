package gym.doubles;

import gym.Member;
import gym.Membership;
import gym.MembershipGateway;

import java.util.ArrayList;
import java.util.List;


public class InMemoryMembershipGateway extends GatewayUtilities<Membership> implements MembershipGateway
{
    public List<Membership> findMembershipForMember(Member member)
    {
        List<Membership> results = new ArrayList<Membership>();
        for (Membership membership : getEntities())
            if (membership.getMember().isSame(member))
                results.add(membership);
        return results;
    }
}
