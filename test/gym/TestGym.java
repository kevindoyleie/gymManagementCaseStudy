package gym;


import gym.doubles.InMemoryMembershipGateway;
import gym.doubles.InMemoryUserGateway;
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
    private User user;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy");
    Calendar today = Calendar.getInstance();

    @Before
    public void setUp() throws Exception
    {
        user = new User();
        setupContext();
    }

    public static void setupContext()
    {
        Context.userGateway = new InMemoryUserGateway();
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
        user.setUserName("Kevin Doyle");
        user.setType("member");
        user.setPhoneNumber("0868579577");
        user.setHeight("183");
        user.setWeight("114kg");
        Context.userGateway.save(user);

        User kevin = Context.userGateway.findUserByName("Kevin Doyle");
        Membership membership = new Membership(NINTYNINEDAYS, kevin);
        membership.setJoinDate(dateFormat.parse("28/10/2014"));
        Context.membershipGateway.save(membership);

        assertEquals("Kevin Doyle", kevin.getUserName());
        assertFalse(kevin.isAdmin());
        assertEquals("0868579577", kevin.getPhoneNumber());
        assertEquals("183", kevin.getHeight());
        assertEquals("114kg", kevin.getWeight());
        assertTrue(kevin.isMember(NINTYNINEDAYS, kevin));
        assertEquals(dateFormat.parse("28/10/2014"), kevin.getJoinDate(NINTYNINEDAYS, kevin));
        assertEquals(dateFormat.parse("04/02/2015"), kevin.getExpiryDate(kevin));
    }

    @Test
    public void createNewAdminUser() throws Exception
    {
        user.setUserName("James Fennelly");
        user.setType("admin");
        user.setPhoneNumber("999");
        user.setHeight("189");
        user.setWeight("118kg");
        Context.userGateway.save(user);

        User james = Context.userGateway.findUserByName("James Fennelly");
        Membership membership = new Membership(TRAINER, james);
        membership.setJoinDate(dateFormat.parse("1/1/2012"));
        Context.membershipGateway.save(membership);

        assertEquals("James Fennelly", james.getUserName());
        assertTrue(james.isAdmin());
        assertEquals("999", james.getPhoneNumber());
        assertEquals("189", james.getHeight());
        assertEquals("118kg", james.getWeight());
        assertTrue(james.isMember(TRAINER, james));
    }

    @Test
    public void createNewOneYearMember() throws Exception
    {
        user.setUserName("Alan Doyle");
        user.setType("member");
        user.setPhoneNumber("0868579577");
        user.setHeight("181");
        user.setWeight("93.5kg");
        Context.userGateway.save(user);

        User alan = Context.userGateway.findUserByName("Alan Doyle");
        Membership membership = new Membership(YEAR, alan);
        membership.setJoinDate(today.getTime());
        Context.membershipGateway.save(membership);

        assertEquals("Alan Doyle", alan.getUserName());
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
        user.setUserName("Sean Dowd");
        user.setType("member");
        user.setPhoneNumber("0868579577");
        user.setHeight("180");
        user.setWeight("91kg");
        Context.userGateway.save(user);

        User sean = Context.userGateway.findUserByName("Sean Dowd");
        Membership membership = new Membership(THREEMONTHS, sean);
        membership.setJoinDate(today.getTime());
        Context.membershipGateway.save(membership);

        assertEquals("Sean Dowd", sean.getUserName());
        assertFalse(sean.isAdmin());
        assertTrue(sean.isMember(THREEMONTHS, sean));
        assertEquals(today.getTime(), sean.getJoinDate(THREEMONTHS, sean));
        assertEquals(threeMonthsFromToday(), sean.getExpiryDate(sean));
    }

    @Test
    public void expiredMember() throws Exception
    {
        user.setUserName("Harry Doyle");
        user.setType("Member");
        user.setHeight("100");
        user.setWeight("25kg");
        Context.userGateway.save(user);

        User harry = Context.userGateway.findUserByName("Harry Doyle");
        Membership membership = new Membership(MONTH, harry);
        membership.setJoinDate(dateFormat.parse("23/9/2014"));
        Context.membershipGateway.save(membership);

        assertFalse(harry.isMember(MONTH, harry));
    }

}
