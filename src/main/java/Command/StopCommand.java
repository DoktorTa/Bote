package Command;

import Users.NoVerUserBot;
import Users.VerUserBot;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;


/**
 * Класс ответственный за команду остановки общения с администратором и удаление из верифицированных пользователей.
 */
public class StopCommand extends AbsUserOperationCommand{

    public StopCommand(VerUserBot verUsersGroup, NoVerUserBot noVerUsersGroup) {
        super("/stop", "Stops communication with the administrator, ATTENTION! You are losing your verification.", verUsersGroup, noVerUsersGroup);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {

        //TODO: а не верифицированные?
        if (!userIsAdmin(user, chat)){
            String textMSG = delInVerGroup(user);
            sendMsg(absSender, textMSG, chat);
        }
    }


    /**
     * Удаление пользователя из списка верифицированных.
     * @param user пользователь телеграмма.
     * @return String текст ответа.
     */
    private String delInVerGroup(User user){
        mVerUsersGroup.removeUserBot(user);
        return "STOP";
    }
}
