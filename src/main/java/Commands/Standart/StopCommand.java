package Commands.Standart;

import Commands.AbstractCommand;
import Users.IUsersRepository;
import Users.UserBot;

public class StopCommand extends AbstractCommand {

    public StopCommand(IUsersRepository userBot) {
        super("/stop", "Stops communication with the administrator, ATTENTION! You are losing your verification.", userBot);
    }

    /**
     * Удаление пользователя из списка верифицированных.
     * @param user пользователь телеграмма.
     * @return String текст ответа.
     */
    private String delInVerGroup(UserBot user){
        usersBot.removeUserToVerifiedUsers(user.getIdentifier());
        return "STOP";
    }

    @Override
    public String getAnswer(UserBot user, String[] strings) {
        String textMSG = "No no no, you admin.";

        //TODO: а не верифицированные?
        if (!userIsAdmin(user.getIdentifier())){
            textMSG = delInVerGroup(user);
        }

        return textMSG;
    }
}
