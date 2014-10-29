package gym;


import java.lang.String;import java.util.Date;
import java.util.List;

public class User extends Entity
{
    private String userName;
    private String type;
    private boolean isAdmin;
    private String phoneNumber;
    private String weight;
    private String height;

    public User()
    {}

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public void setType(String type)
    {
        this.type = type;
        this.isAdmin = this.type.equals("admin");
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

    public boolean isMember(Membership.MemberType memberType, User user)
    {
        List<Membership> members = Context.membershipGateway.findMembershipForUser(user);
        for (Membership m : members) {
            if (m.getType() == memberType && m.isMember()) {
                return true;
            }
        }
        return false;
    }

    public Date getJoinDate(Membership.MemberType memberType, User user)
    {
        List<Membership> members = Context.membershipGateway.findMembershipForUser(user);
        for (Membership m : members) {
            if (m.getType() == memberType) {
                return m.getJoinDate();
            }
        }
        return null;
    }

    public Date getExpiryDate(User user)
    {
        List<Membership> members = Context.membershipGateway.findMembershipForUser(user);
        for (Membership m : members) {
            if (m.getUser() == user) {
                return m.getExpiryDate();
            }
        }
        return null;
    }
}
