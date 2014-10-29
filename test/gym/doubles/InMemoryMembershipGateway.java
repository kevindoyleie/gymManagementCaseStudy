package gym.doubles;

import gym.Membership;
import gym.MembershipGateway;
import gym.User;

import java.util.ArrayList;
import java.util.List;


public class InMemoryMembershipGateway extends GatewayUtilities<Membership> implements MembershipGateway
{
    public List<Membership> findMembershipForUser(User user)
    {
        List<Membership> results = new ArrayList<Membership>();
        for (Membership member : getEntities()) {
            if (member.getUser().isSame(user))
                results.add(member);
        }
        return results;
    }
}
