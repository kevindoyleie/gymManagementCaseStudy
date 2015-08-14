package usecases.memberDetails;

import gym.Context;
import gym.entities.Member;
import gym.entities.Membership;
import usecases.memberSummaries.MemberSummariesPresenter;

public class MemberDetailsUseCase
{
    public PresentableMemberDetails requestMemberDetails(String name)
    {
        PresentableMemberDetails details = new PresentableMemberDetails();
        Member member = Context.memberGateway.findMemberByName(name);
        Membership membership = Context.membershipGateway.findMembershipForMember(member);

        if (member == null || membership == null) {
            details.wasFound = false;
            return details;
        }
        else {
            details.wasFound = true;
            MemberSummariesPresenter.formatSummaryFields(member, membership, details);
            return details;
        }
    }
}
