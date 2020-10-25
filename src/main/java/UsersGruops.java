import org.telegram.telegrambots.meta.api.objects.User;

import java.util.HashSet;
import java.util.Set;

abstract public class UsersGruops {
    /* Parent class for all kinds of users. */
    protected Set<UserBot> usersGroup;

    public UsersGruops(){
        usersGroup = new HashSet<>();
    }

    public String getUserList(){
        return "";
    }

    abstract public boolean addUserBot(UserBot userBot);

    abstract public boolean removeUserBot(User user);

}
