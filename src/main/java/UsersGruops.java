import org.telegram.telegrambots.meta.api.objects.User;

import java.util.HashSet;
import java.util.Set;

abstract public class UsersGruops {
    /* Parent class for all kinds of users. */
    protected Set<UserBot> usersGroup;

    public UsersGruops(){
        usersGroup = new HashSet<>();
    }

    public String getUsersGroupString(){
        StringBuilder UsersGroupString = new StringBuilder();
        int inc = 0;

        for (UserBot user: usersGroup){
            UsersGroupString.append(inc).append("|").append(user.identifier).append("\n");
            inc++;
        }

        return UsersGroupString.toString();
    }

    abstract public boolean addUserBot(UserBot userBot);

    abstract public boolean removeUserBot(User user);

}
