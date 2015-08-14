package usecases.memberSummaries;

import gym.Context;
import gym.entities.Member;
import gym.entities.Membership;

import java.util.ArrayList;
import java.util.List;

public class MemberSummariesUseCase
{
    public static boolean isMember(Membership.MemberType memberType, Member member)
    {
        List<Membership> memberships = Context.membershipGateway.findMembershipsForMember(member);
        for (Membership m : memberships) {
            if (m.getType() == memberType)
                return true;
        }
        return false;
    }

    public List<PresentableMemberSummary> presentableMemberships(Member member)
    {
        ArrayList<PresentableMemberSummary> presentableMemberSummaries = new ArrayList<PresentableMemberSummary>();
        List<Membership> allMemberships = Context.membershipGateway.findAllMemberships();

        for (Membership membership : allMemberships) {
            presentableMemberSummaries.add(MemberSummariesPresenter.formatCodecast(member, membership));
        }
        return presentableMemberSummaries;
    }

    public boolean isExpired(Membership.MemberType memberType, Member member)
    {
        List<Membership> memberships = Context.membershipGateway.findMembershipsForMember(member);
        if (memberships.isEmpty()) return true;

        for (Membership m : memberships)
            if (m.getType() == memberType && m.isExpired())
                return true;
        return false;
    }
}
