package Users;

import org.telegram.telegrambots.meta.api.objects.User;

import java.util.HashSet;
import java.util.Objects;
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
            UsersGroupString.append("[").append(inc).append("]")
                    .append("|").append(user.identifier)
                    .append("|").append(user.getUser().getUserName()).append("\n");
            inc++;
        }

        return UsersGroupString.toString();
    }

    public boolean userInGroup(UserBot userBot){
        return usersGroup.stream().anyMatch(x -> Objects.equals(x.identifier, userBot.identifier));
    }

    abstract public boolean addUserBot(UserBot userBot);

    abstract public boolean removeUserBot(User user);

}
