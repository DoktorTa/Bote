package Users;

import org.telegram.telegrambots.meta.api.objects.User;

public class VerUserBot extends UsersGruops{
    private UserBot admin = null;

    public VerUserBot(){
        super();
    }

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

    public UserBot getAdmin(){
        return admin;
    }
}
