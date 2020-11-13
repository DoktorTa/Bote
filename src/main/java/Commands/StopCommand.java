package Commands;

import Users.IUsersOperation;
import Users.NoVerUserBot;
import Users.UserBot;
import Users.VerUserBot;
import org.telegram.telegrambots.meta.api.objects.User;

public class StopCommand extends AbsCommand{

    public StopCommand(IUsersOperation userBot) {
        super("/stop", "Stops communication with the administrator, ATTENTION! You are losing your verification.", userBot);
    }

    /**
     * Удаление пользователя из списка верифицированных.
     * @param user пользователь телеграмма.
     * @return String текст ответа.
     */
    private String delInVerGroup(UserBot user){
        usersBot.removeUserToVerGroup(user.identifier);
        return "STOP";
    }

    @Override
    public String getAnswer(UserBot user, String[] strings) {
        String textMSG = "";

        //TODO: а не верифицированные?
        if (!userIsAdmin(user.identifier)){
            textMSG = delInVerGroup(user);
        }

        return textMSG;
    }
}
