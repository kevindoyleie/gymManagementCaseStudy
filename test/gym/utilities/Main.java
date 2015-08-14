package gym.utilities;


import gym.TestSetup;
import gym.socketserver.SocketServer;
import gym.view.ViewTemplate;

import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        TestSetup.setupSampleData();
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

    private static String makeResponse(String content)
    {
        return "HTTP/1.1 200 OK\n" +
                "Content-Length: " + content.length() + "\n" +
                "\n" +
                content;
    }

    public static String getFrontPage()
    {
        try {
            ViewTemplate frontPageTemplate = ViewTemplate.create("resources/html/frontpage.html");
            ViewTemplate memberTemplate = ViewTemplate.create("html/member.html");

            memberTemplate.replace("title", "Episode 1: The Beginning!");

            String memberView = memberTemplate.getContent(); // for the moment
            frontPageTemplate.replace("codecasts", memberView);
            return frontPageTemplate.getContent();
        } catch(IOException e) {
            e.printStackTrace();
            return "Gunk";
        }
    }
}
