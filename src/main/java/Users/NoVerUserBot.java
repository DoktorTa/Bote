package Users;

import org.telegram.telegrambots.meta.api.objects.User;

/** 10 последних не верифицированных пользователей.
 *
 */
public class NoVerUserBot extends UsersGroups {

    public NoVerUserBot(){
        super();
    }

    @Override
    public boolean addUserBot(UserBot userBot) {

        if (moreTenUsersOrNoEmpty()) {
            usersGroup.remove(usersGroup.iterator().next());
        }

        return usersGroup.add(userBot);
    }

    /**
     * @return Не пуст ли список и нет ли в нем больше 10 пользователей.
     */
    private boolean moreTenUsersOrNoEmpty(){
        int maxNoVerUsers = 10;
        return !usersGroup.isEmpty() && usersGroup.size() >= maxNoVerUsers;
    }

    @Override
    public boolean removeUserBot(String identifier) {
        return usersGroup.removeIf(x -> x.getIdentifier().equals(identifier));
    }

}
