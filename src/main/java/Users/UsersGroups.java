package Users;

import java.util.HashSet;
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
     * @return Список пользователей в виде "[#]|Identifier|"
     */
    public String getUsersGroupString(){
        StringBuilder UsersGroupString = new StringBuilder();
        int inc = 0;

        for (UserBot user: usersGroup){
            UsersGroupString.append("[").append(inc).append("]")
                    .append("|").append(user.getIdentifier())
                    .append("|").append("\n");
            inc++;
        }

        return UsersGroupString.toString();
    }

    /**
     * @param identifier Идентификатор пользователя бота. ("FirstNameUserName")
     * @return userBot Пользователь or null.
     */
    public UserBot searchUserBot(String identifier){
        return usersGroup.stream().filter(x -> x.getIdentifier().equals(identifier)).findFirst().orElse(null);
    }

    /**
     * @param userBot пользователь.
     * @return завершена ли операция удачно.
     */
    abstract public boolean addUserBot(UserBot userBot);

    /**
     * @return завершена ли операция удачно.
     */
    abstract public boolean removeUserBot(String identifier);
}