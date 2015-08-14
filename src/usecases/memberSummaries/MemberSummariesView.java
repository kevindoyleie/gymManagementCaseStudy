package usecases.memberSummaries;

import gym.view.ViewTemplate;

import java.io.IOException;
import java.util.List;

public class MemberSummariesView
{
    static String toHTML(List<PresentableMemberSummary> presentableMemberSummaries)
    {
        try {
            ViewTemplate frontPageTemplate = ViewTemplate.create("html/frontpage.html");

            StringBuilder membershipLines = new StringBuilder();
            for (PresentableMemberSummary presentableMemberSummary : presentableMemberSummaries) {
                ViewTemplate memberTemplate = ViewTemplate.create("html/member.html");
                memberTemplate.replace("name", presentableMemberSummary.name);
                memberTemplate.replace("joinDate", presentableMemberSummary.joinDate);
                membershipLines.append(memberTemplate.getContent());
            }
            frontPageTemplate.replace("members", membershipLines.toString());
            return frontPageTemplate.getContent();
        }
        catch (IOException e) {
            e.printStackTrace();
            return "Gunk";
        }
    }
}
