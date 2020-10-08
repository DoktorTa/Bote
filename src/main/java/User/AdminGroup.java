package User;

import org.telegram.telegrambots.meta.api.objects.User;

public class AdminGroup extends AbsGroup{

    public AdminGroup(){
        super();
    }

    @Override
    public boolean addUserToGroup(UserBot userBot) {
        userBot.setStatus(0);
        return mGroupUsers.add(userBot);
    }

    @Override
    public boolean removeUserToGroup(User user) {
        return mGroupUsers.removeIf(a -> a.getUser().equals(user));
    }
}
