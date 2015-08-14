package gym.entities;


import gym.Context;

import java.util.Date;
import java.util.List;

public class Member extends Entity
{
    private String memberName;
    private String type;
    private boolean isAdmin;
    private String phoneNumber;
    private String weight;
    private String height;

    public Member()
    {}

    public Member(String memberName)
    {
        this.memberName = memberName;
    }

    public String getMemberName()
    {
        return memberName;
    }

    public void setMemberName(String memberName)
    {
        this.memberName = memberName;
    }

    public void setType(String type)
    {
        this.type = type;
        this.isAdmin = this.type.equals("admin");
    }

    public String getType()
    {
        return type;
    }

    public String getPhoneNumber()
    {

        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getWeight()
    {
        return weight;
    }

    public void setWeight(String weight)
    {
        this.weight = weight;
    }

    public String getHeight()
    {
        return height;
    }

    public void setHeight(String height)
    {
        this.height = height;
    }

    public boolean isAdmin()
    {
        return isAdmin;
    }

    public boolean isMember(Membership.MemberType memberType, Member member)
    {
        List<Membership> members = Context.membershipGateway.findMembershipsForMember(member);
        for (Membership m : members)
            if (m.getType() == memberType && m.isMember())
                return true;
        return false;
    }

    public Date getJoinDate(Membership.MemberType memberType, Member member)
    {
        List<Membership> members = Context.membershipGateway.findMembershipsForMember(member);
        for (Membership m : members)
            if (m.getType() == memberType)
                return m.getJoinDate();
        return null;
    }

    public Date getExpiryDate(Member member)
    {
        List<Membership> members = Context.membershipGateway.findMembershipsForMember(member);
        for (Membership m : members)
            if (m.getMember() == member)
                return m.getExpiryDate();
        return null;
    }
}
