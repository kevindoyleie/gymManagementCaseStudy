package gym;

import gym.doubles.InMemoryMemberGateway;
import gym.doubles.InMemoryMembershipGateway;
import gym.entities.Member;
import gym.entities.Membership;

import java.text.SimpleDateFormat;
import java.util.Date;

import static gym.entities.Membership.MemberType.THREEMONTHS;
import static gym.entities.Membership.MemberType.YEAR;

public class TestSetup
{
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy");

    public static void setupContext() {
        Context.memberGateway = new InMemoryMemberGateway();
        Context.membershipGateway = new InMemoryMembershipGateway();
        Context.gateKeeper = new GateKeeper();
    }

    public static void setupSampleData() throws Exception
    {
        setupContext();

        Member member1 = new Member("Kevin Doyle");
        member1.setType("member");
        member1.setPhoneNumber("0868579577");
        member1.setHeight("183");
        member1.setWeight("114kg");
        Member member2 = new Member("Alan Doyle");
        member2.setType("member");
        member2.setPhoneNumber("0868579577");
        member2.setHeight("183");
        member2.setWeight("114kg");

        Context.memberGateway.save(member1);
        Context.memberGateway.save(member2);

        Membership m1 = new Membership(THREEMONTHS, member1);
        m1.setJoinDate(new Date());


        Membership m2 = new Membership(YEAR, member2);
        m2.setJoinDate(dateFormat.parse("15/03/2014"));

        Context.membershipGateway.save(m1);
        Context.membershipGateway.save(m2);
    }
}
