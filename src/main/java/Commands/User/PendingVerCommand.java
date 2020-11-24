package Commands.User;

import Commands.AbstractCommand;
import Users.IUsersRepository;
import Users.UserBot;


public class PendingVerCommand extends AbstractCommand {

    public PendingVerCommand(IUsersRepository usersBot){
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
