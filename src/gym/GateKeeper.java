package gym;


public class GateKeeper
{
    private Member loggedInMember;

    public void setLoggedInMember(Member loggedInMember)
    {
        this.loggedInMember = loggedInMember;
    }

    public Member getLoggedInMember()
    {
        return loggedInMember;
    }
}
