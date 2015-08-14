package gym.usecases.memberDetails;


import gym.Context;
import gym.TestSetup;
import gym.entities.Member;
import gym.entities.Membership;
import org.junit.Before;
import org.junit.Test;
import usecases.memberDetails.MemberDetailsUseCase;
import usecases.memberDetails.PresentableMemberDetails;
import usecases.memberSummaries.MemberSummariesPresenter;

import static gym.entities.Membership.MemberType.YEAR;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class MemberDetailsUseCaseTest
{
    private Member member;

    @Before
    public void setUp() throws Exception
    {
        TestSetup.setupContext();
        member = Context.memberGateway.save(new Member("Member"));
    }

    @Test
    public void createsMemberDetailsPresentation() throws Exception
    {
        Membership membership = new Membership(YEAR, member);
        membership.setJoinDate(MemberSummariesPresenter.dateFormat.parse("8/14/2015"));
        Context.membershipGateway.save(membership);

        MemberDetailsUseCase useCase = new MemberDetailsUseCase();
        PresentableMemberDetails details = useCase.requestMemberDetails("Member");

        assertEquals("Member", details.name);
        assertEquals("8/14/2015", details.joinDate);
    }

    @Test
    public void doesntCrashOnMissingMembership() throws Exception
    {
        MemberDetailsUseCase useCase = new MemberDetailsUseCase();
        PresentableMemberDetails details = useCase.requestMemberDetails("Member");

        assertFalse(details.wasFound);
        assertFalse(details.isMember);

    }
}
