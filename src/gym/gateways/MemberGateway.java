package gym.gateways;

import gym.entities.Member;

import java.util.List;

public interface MemberGateway
{
    Member save(Member member);

    Member findMemberByName(String name);

    List<Member> findMembersByName(String name);

    Member findMemberByPhoneNumber(String phoneNumber);

    List<Member> findAllMembers();
}
