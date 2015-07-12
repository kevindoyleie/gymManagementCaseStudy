package gym.doubles;


import gym.entities.Member;
import gym.gateways.MemberGateway;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

public class InMemoryMemberGateway extends GatewayUtilities<Member>  implements MemberGateway
{
    public Member findMemberByName(String username)
    {
        for (Member member : getEntities())
            if (member.getMemberName().equals(username))
                return member;
        return null;
    }

    @Override
    public List<Member> findMembersByName(String name)
    {
        List<Member> members = new ArrayList<>();
        for (Member member : getEntities())
            if (member.getMemberName().equals(name))
                members.add(member);
        return members;
    }

    @Override
    public Member findMemberByPhoneNumber(String phoneNumber)
    {
        for (Member member : getEntities())
            if (member.getPhoneNumber().equals(phoneNumber))
                return member;
        return null;
    }

}
