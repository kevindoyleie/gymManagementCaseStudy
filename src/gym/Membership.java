package gym;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static gym.Membership.MemberType.NINTYNINEDAYS;
import static gym.Membership.MemberType.MONTH;
import static gym.Membership.MemberType.THREEMONTHS;
import static gym.Membership.MemberType.YEAR;

public class Membership extends Entity
{
    public enum MemberType
    {
        TRAINER, YEAR, NINTYNINEDAYS, THREEMONTHS, MONTH
    }

    private MemberType memberType;
    private User user;
    private Date joinDate = new Date();
    private Date expiryDate = new Date();
    private Calendar calendar = GregorianCalendar.getInstance();

    public Membership(MemberType memberType, User user)
    {
        this.memberType = memberType;
        this.user = user;
    }

    public User getUser()
    {
        return user;
    }

    public MemberType getType()
    {
        return memberType;
    }

    public void setJoinDate(Date joinDate)
    {
        this.joinDate = joinDate;
        setExpiryDate();
    }

    public Date getJoinDate()
    {
        return joinDate;
    }

    public Date getExpiryDate()
    {
        return expiryDate;
    }

    public boolean isMember()
    {
        Calendar today = Calendar.getInstance();
        return expiryDate.after(today.getTime());
    }

    private void setExpiryDate()
    {
        calendar.setTime(joinDate);
        if (memberType.equals(YEAR))
            calendar.add(Calendar.YEAR, 1);
        else if (memberType.equals(NINTYNINEDAYS))
            calendar.add(Calendar.DAY_OF_MONTH, 99);
        else if (memberType.equals(THREEMONTHS))
            calendar.add(Calendar.MONTH, 3);
        else if (memberType.equals(MONTH))
            calendar.add(Calendar.MONTH, 1);
        else
            calendar.add(Calendar.YEAR, 100);

        expiryDate.setTime(calendar.getTimeInMillis());
    }

}
