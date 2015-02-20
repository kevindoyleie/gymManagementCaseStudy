package gym;


import gym.doubles.InMemoryMemberGateway;
import gym.doubles.InMemoryMembershipGateway;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static gym.Membership.MemberType.MONTH;
import static gym.Membership.MemberType.NINTYNINEDAYS;
import static gym.Membership.MemberType.THREEMONTHS;
import static gym.Membership.MemberType.TRAINER;
import static gym.Membership.MemberType.YEAR;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestGym
{
    private Member member;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy");
    Calendar today = Calendar.getInstance();

    @Before
    public void setUp() throws Exception
    {
        member = new Member();
        setupContext();
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

    public static void setupContext()
    {
        Context.memberGateway = new InMemoryMemberGateway();
        Context.membershipGateway = new InMemoryMembershipGateway();
    }

    private Date nintyNineDaysFromToday()
    {
        today.add(Calendar.DAY_OF_MONTH, 99);
        return today.getTime();
    }

    private Date threeMonthsFromToday()
    {
        today.add(Calendar.MONTH, 3);
        return today.getTime();
    }

    private Date oneYearFromToday()
    {
        today.add(Calendar.YEAR, 1);
        return today.getTime();
    }

    @Test
    public void createNew99DayMember() throws Exception
    {
        member.setMemberName("Kevin Doyle");
        member.setType("member");
        member.setPhoneNumber("0868579577");
        member.setHeight("183");
        member.setWeight("114kg");
        Context.memberGateway.save(member);

        Member kevin = Context.memberGateway.findMemberByName("Kevin Doyle");
        Membership membership = new Membership(NINTYNINEDAYS, kevin);
        membership.setJoinDate(dateFormat.parse("28/10/2014"));
        Context.membershipGateway.save(membership);

        assertEquals("Kevin Doyle", kevin.getMemberName());
        assertFalse(kevin.isAdmin());
        assertEquals("0868579577", kevin.getPhoneNumber());
        assertEquals("183", kevin.getHeight());
        assertEquals("114kg", kevin.getWeight());
        // Membership has expired.
        assertFalse(kevin.isMember(NINTYNINEDAYS, kevin));
        assertEquals(dateFormat.parse("28/10/2014"), kevin.getJoinDate(NINTYNINEDAYS, kevin));
        assertEquals(dateFormat.parse("04/02/2015"), kevin.getExpiryDate(kevin));
    }

    @Test
    public void createNewAdminUser() throws Exception
    {
        member.setMemberName("James Fennelly");
        member.setType("admin");
        member.setPhoneNumber("999");
        member.setHeight("189");
        member.setWeight("118kg");
        Context.memberGateway.save(member);

        Member james = Context.memberGateway.findMemberByName("James Fennelly");
        Membership membership = new Membership(TRAINER, james);
        membership.setJoinDate(dateFormat.parse("1/1/2012"));
        Context.membershipGateway.save(membership);

        assertEquals("James Fennelly", james.getMemberName());
        assertTrue(james.isAdmin());
        assertEquals("999", james.getPhoneNumber());
        assertEquals("189", james.getHeight());
        assertEquals("118kg", james.getWeight());
        assertTrue(james.isMember(TRAINER, james));
    }

    @Test
    public void createNewOneYearMember() throws Exception
    {
        member.setMemberName("Alan Doyle");
        member.setType("member");
        member.setPhoneNumber("0868579577");
        member.setHeight("181");
        member.setWeight("93.5kg");
        Context.memberGateway.save(member);

        Member alan = Context.memberGateway.findMemberByName("Alan Doyle");
        Membership membership = new Membership(YEAR, alan);
        membership.setJoinDate(today.getTime());
        Context.membershipGateway.save(membership);

        assertEquals("Alan Doyle", alan.getMemberName());
        assertFalse(alan.isAdmin());
        assertEquals("0868579577", alan.getPhoneNumber());
        assertEquals("181", alan.getHeight());
        assertEquals("93.5kg", alan.getWeight());
        assertTrue(alan.isMember(YEAR, alan));
        assertEquals(today.getTime(), alan.getJoinDate(YEAR, alan));
        assertEquals(oneYearFromToday(), alan.getExpiryDate(alan));
    }

    @Test
    public void createNewThreeMonthMember() throws Exception
    {
        member.setMemberName("Sean Dowd");
        member.setType("member");
        member.setPhoneNumber("0868579577");
        member.setHeight("180");
        member.setWeight("91kg");
        Context.memberGateway.save(member);

        Member sean = Context.memberGateway.findMemberByName("Sean Dowd");
        Membership membership = new Membership(THREEMONTHS, sean);
        membership.setJoinDate(today.getTime());
        Context.membershipGateway.save(membership);

        assertEquals("Sean Dowd", sean.getMemberName());
        assertFalse(sean.isAdmin());
        assertTrue(sean.isMember(THREEMONTHS, sean));
        assertEquals(today.getTime(), sean.getJoinDate(THREEMONTHS, sean));
        assertEquals(threeMonthsFromToday(), sean.getExpiryDate(sean));
    }

    @Test
    public void expiredMember() throws Exception
    {
        member.setMemberName("Harry Doyle");
        member.setType("Member");
        member.setHeight("100");
        member.setWeight("25kg");
        Context.memberGateway.save(member);

        Member harry = Context.memberGateway.findMemberByName("Harry Doyle");
        Membership membership = new Membership(MONTH, harry);
        membership.setJoinDate(dateFormat.parse("23/9/2014"));
        Context.membershipGateway.save(membership);

        assertFalse(harry.isMember(MONTH, harry));
    }


}
