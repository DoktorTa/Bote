package Commands;

import Users.IUsersOperation;
import Users.UserBot;


public class PendingVerCommand extends AbsCommand{

    public PendingVerCommand(IUsersOperation usersBot){
        super("/pending_ver", "Displays users awaiting verification.", usersBot);
    }

    @Override
    public String getAnswer(UserBot user, String[] strings) {
        if (userIsAdmin(user.getIdentifier())) {
            return getBeadrollNoVerUser();
        }

        return "";
    }

    /**
     * @return String список не верифицированных пользователей.
     */
    private String getBeadrollNoVerUser(){
        return usersBot.getUsersGroupString();
    }
}
