package gym;


import gym.entities.Member;

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
