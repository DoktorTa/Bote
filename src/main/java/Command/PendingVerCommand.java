package Command;

import Users.NoVerUserBot;
import Users.VerUserBot;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

/**
 * Класс ответственный за команду вывода списка не верифицированных пользователей.
 */
public class PendingVerCommand extends AbsUserOperationCommand {

    public PendingVerCommand(VerUserBot verUsersGroup, NoVerUserBot noVerUsersGroup) {
        super("/pending_ver", "Displays users awaiting verification.",
                verUsersGroup, noVerUsersGroup);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {

        if (userIsAdmin(user, chat)){
            String textMSG = getBeadrollNoVerUser();
            sendMsg(absSender, textMSG, chat);
        }

    }

    /**
     * @return String список не верифицированных пользователей.
     */
    private String getBeadrollNoVerUser(){
        return mNoVerUsersGroup.getUsersGroupString();
    }
}
