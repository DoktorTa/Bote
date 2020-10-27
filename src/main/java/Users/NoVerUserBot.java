package Users;

import org.telegram.telegrambots.meta.api.objects.User;

public class NoVerUserBot extends UsersGroups {

    public NoVerUserBot(){
        super();
    }

    @Override
    public boolean addUserBot(UserBot userBot) {
        int maxNoVerUsers = 10;

        if (!usersGroup.isEmpty() && usersGroup.size() >= maxNoVerUsers) {
            Boolean x = usersGroup.remove(usersGroup.iterator().next());
        }

        return usersGroup.add(userBot);
    }

    @Override
    public boolean removeUserBot(User user) {
        return usersGroup.removeIf(x -> x.getUser().equals(user));
    }

}
