package gym.utilities;


import gym.Context;
import gym.entities.Member;
import gym.entities.Membership;
import gym.TestGym;
import gym.socketserver.SocketServer;
import gym.view.ViewTemplate;

import java.io.IOException;
import java.util.List;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        TestGym.setupSampleData();
        SocketServer server = new SocketServer(8080, s ->
        {
            try {
                String frontPage = getFrontPage();
                String response = makeResponse(frontPage);
                s.getOutputStream().write(response.getBytes());
            } catch(IOException e) {
                e.printStackTrace();
            }
        });
        server.start();
    }

    private static String makeResponse(String content) {
        return "HTTP/1.1 200 OK\n" +
                "Content-Length: " + content.length() + "\n" +

                "\n" +
                content;
    }

    public static String getFrontPage()
    {
        Member member = Context.memberGateway.findMemberByName("Kevin Doyle");
        List<Membership> members = Context.membershipGateway.findMembershipForMember(member);


        try {
            ViewTemplate frontPageTemplate = ViewTemplate.create("html/frontpage.html");

            StringBuilder memberLines = new StringBuilder();
            for(Membership m : members) {
                if (m.getMember() == member) {
                    ViewTemplate memberTemplate = ViewTemplate.create("html/member.html");
                    memberTemplate.replace("name", m.getMember().getMemberName());
                    memberTemplate.replace("memberType", m.getMember().getType());
                    memberTemplate.replace("expDate", String.valueOf(m.getExpiryDate()));
                    memberLines.append(memberTemplate.getContent());
                }
            }

            frontPageTemplate.replace("codecasts", memberLines.toString());
            return frontPageTemplate.getContent();
        } catch(IOException e) {
            e.printStackTrace();
            return "Gunk";
        }
    }
}
