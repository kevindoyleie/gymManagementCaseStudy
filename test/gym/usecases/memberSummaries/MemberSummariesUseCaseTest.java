package gym.usecases.memberSummaries;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import gym.Context;
import gym.TestSetup;
import gym.entities.Member;
import gym.entities.Membership;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import usecases.memberSummaries.MemberSummariesUseCase;
import usecases.memberSummaries.PresentableMemberSummary;

import java.util.GregorianCalendar;
import java.util.List;

import static gym.entities.Membership.MemberType.YEAR;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(HierarchicalContextRunner.class)
public class MemberSummariesUseCaseTest
{
    private Member member;
    private MemberSummariesUseCase useCase;

    @Before
    public void setUp() throws Exception
    {
        TestSetup.setupContext();
        member = Context.memberGateway.save(new Member("Member"));
        useCase = new MemberSummariesUseCase();
    }

    public class GivenNoMemberships
    {
        @Test
        public void noneArePresented() throws Exception
        {
            List<PresentableMemberSummary> presentableMemberSummaries
                    = useCase.presentableMemberships(member);

            assertEquals(0, presentableMemberSummaries.size());
        }
    }

    public class GivenOneMembership
    {
        private Membership membership;

        @Before
        public void setUpMembership() throws Exception
        {
            membership = Context.membershipGateway.save(new Membership(YEAR, member));
        }

        @Test
        public void oneIsPresented() throws Exception
        {
            membership.setJoinDate(new GregorianCalendar(2014, 4, 19).getTime());
            Context.membershipGateway.save(membership);

            List<PresentableMemberSummary> presentableMemberSummaries = useCase.presentableMemberships(member);

            assertEquals(1, presentableMemberSummaries.size());
            PresentableMemberSummary presentableMemberSummary = presentableMemberSummaries.get(0);
            assertEquals("Member", presentableMemberSummary.name);
            assertEquals("5/19/2014", presentableMemberSummary.joinDate);
        }
    }

    public class GivenNoLicenses
    {
        @Test
        public void isAMember() throws Exception
        {
            assertFalse(useCase.isMember(YEAR, member));
            assertTrue(useCase.isExpired(YEAR, member));
        }


    }
}
