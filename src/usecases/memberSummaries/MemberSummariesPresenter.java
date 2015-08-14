package usecases.memberSummaries;

import gym.entities.Member;
import gym.entities.Membership;

import java.text.SimpleDateFormat;

public class MemberSummariesPresenter
{
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("M/dd/yyyy");

    public static void formatSummaryFields(Member member, Membership membership,
                                           PresentableMemberSummary cc)
    {
        cc.name = membership.getMember().getMemberName();
        cc.joinDate = dateFormat.format(membership.getJoinDate());
        cc.isMember = MemberSummariesUseCase.isMember(membership.getType(), member);
        cc.isExpired = !cc.isExpired;
    }


    public static PresentableMemberSummary formatCodecast(Member member, Membership membership)
    {
        PresentableMemberSummary cc = new PresentableMemberSummary();
        MemberSummariesPresenter.formatSummaryFields(member, membership, cc);
        return cc;
    }
}
