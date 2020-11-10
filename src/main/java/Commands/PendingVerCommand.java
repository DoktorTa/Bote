package Commands;

import Users.IUsersOperation;
import Users.UserBot;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;

public class PendingVerCommand extends AbsCommand{

    public PendingVerCommand(IUsersOperation usersBot){
        super("/pending_ver", "Displays users awaiting verification.", usersBot);
    }

    @Override
    public String getAnswer(UserBot user, String[] strings) {
        return getBeadrollNoVerUser();
    }

    /**
     * @return String список не верифицированных пользователей.
     */
    private String getBeadrollNoVerUser(){
        return usersBot.getUsersGroupString();
    }
}
