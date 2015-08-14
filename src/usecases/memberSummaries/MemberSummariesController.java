package usecases.memberSummaries;

import gym.Context;
import gym.entities.Member;
import gym.http.Controller;
import gym.http.ParsedRequest;

public class MemberSummariesController implements Controller
{

    @Override
    public String handle(ParsedRequest request)
    {
        Member kevin = Context.memberGateway.findMemberByName("Kevin");
        MemberSummariesUseCase useCase = new MemberSummariesUseCase();
        MemberSummariesView view = new MemberSummariesView();
        final String html = view.toHTML(useCase.presentableMemberships(kevin));
        return Controller.makeResponse(html);
    }
}
