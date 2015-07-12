package gym.entities;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static gym.entities.Membership.MemberType.NINTYNINEDAYS;
import static gym.entities.Membership.MemberType.MONTH;
import static gym.entities.Membership.MemberType.THREEMONTHS;
import static gym.entities.Membership.MemberType.YEAR;

public class Membership extends Entity
{
    public enum MemberType
    {
        TRAINER, YEAR, NINTYNINEDAYS, THREEMONTHS, MONTH
    }

    private MemberType memberType;
    private Member member;
    private Date joinDate = new Date();
    private Date expiryDate = new Date();
    private Calendar calendar = GregorianCalendar.getInstance();

    public Membership(MemberType memberType, Member member)
    {
        this.memberType = memberType;
        this.member = member;
    }

    public Member getMember()
    {
        return member;
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
