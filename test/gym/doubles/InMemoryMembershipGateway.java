package gym.doubles;

import gym.entities.Member;
import gym.entities.Membership;
import gym.gateways.MembershipGateway;

import java.util.ArrayList;
import java.util.List;


public class InMemoryMembershipGateway extends GatewayUtilities<Membership> implements MembershipGateway
{
    @Override
    public List<Membership> findMembershipsForMember(Member member)
    {
        List<Membership> results = new ArrayList<>();
        for (Membership membership : getEntities())
            if (membership.getMember().isSame(member))
                results.add(membership);
        return results;
    }

    public Membership findMembershipForMember(Member member)
    {
        for (Membership membership : getEntities())
            if (membership.getMember().isSame(member))
                return membership;
        return null;
    }

    @Override
    public List<Membership> findAllMemberships()
    {
        List<Membership> results = new ArrayList<>();
        for (Membership membership : getEntities())
            results.add(membership);
        return results;
    }
}
