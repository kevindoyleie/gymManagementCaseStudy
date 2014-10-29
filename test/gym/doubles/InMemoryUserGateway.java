package gym.doubles;


import gym.User;
import gym.UserGateway;import java.lang.String;

public class InMemoryUserGateway extends GatewayUtilities<User>  implements UserGateway
{
    public User findUserByName(String username)
    {
        for (User user : getEntities()) {
            if (user.getUserName().equals(username)) {
                return user;
            }
        }
        return null;
    }
}