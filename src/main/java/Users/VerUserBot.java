package Users;

import org.telegram.telegrambots.meta.api.objects.User;

/** Верифицированные пользователи.
 *
 * {@value} Admin - администратор бота.
 */
public class VerUserBot extends UsersGroups {
    private UserBot admin = null;

    public VerUserBot(){
        super();
    }

    /**
     * @param userBot пользователь.
     */
    public void setAdmin(UserBot userBot){
        admin = userBot;
    }

    @Override
    public boolean addUserBot(UserBot userBot) {
        return usersGroup.add(userBot);
    }

    @Override
    public boolean removeUserBot(User user) {
        return usersGroup.removeIf(x -> x.getUser().equals(user));
    }

    /**
     * @return пользователь.
     */
    public UserBot getAdmin(){
        return admin;
    }
}
