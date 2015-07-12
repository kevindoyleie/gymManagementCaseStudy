package gym.doubles;

import gym.entities.Member;
import gym.entities.Membership;
import gym.gateways.MembershipGateway;

import java.util.ArrayList;
import java.util.List;


public class InMemoryMembershipGateway extends GatewayUtilities<Membership> implements MembershipGateway
{
    public List<Membership> findMembershipForMember(Member member)
    {
        List<Membership> results = new ArrayList<>();
        for (Membership membership : getEntities())
            if (membership.getMember().isSame(member))
                results.add(membership);
        return results;
    }
}
