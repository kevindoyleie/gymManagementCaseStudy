package gym.socketserver;


import java.net.Socket;

public interface SocketService
{
    public void serve(Socket s);
}
