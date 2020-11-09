package Users;

import org.telegram.telegrambots.meta.api.objects.User;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Класс определяющий стандартные для групп пользователей методы.
 *
 * {@value} usersGroup - HashSet в котором содержатся все пользователи группы.
 */
abstract public class UsersGroups {
    protected Set<UserBot> usersGroup;

    public UsersGroups(){
        usersGroup = new HashSet<>();
    }

    /**
     * @return Список пользователей в виде "[#]|Identifier|UserName"
     */
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

    /**
     * @param userBot Пользователь.
     * @return boolean.
     */
    public boolean userInGroup(UserBot userBot){
        return usersGroup.stream().anyMatch(x -> Objects.equals(x.identifier, userBot.identifier));
    }

    /**
     * @param identifier Идентификатор пользователя бота. ("FirstNameUserName")
     * @return userBot Пользователь or null.
     */
    public UserBot searchUserBot(String identifier){
        return usersGroup.stream().filter(x -> x.identifier.equals(identifier)).findFirst().orElse(null);
    }

    /**
     * @param userBot пользователь.
     * @return завершена ли операция удачно.
     */
    abstract public boolean addUserBot(UserBot userBot);

    /**
     * @param user пользователь телеграмма.
     * @return завершена ли операция удачно.
     */
    abstract public boolean removeUserBot(User user);
}