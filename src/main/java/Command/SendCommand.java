package Command;

import Users.NoVerUserBot;
import Users.UserBot;
import Users.VerUserBot;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.util.Arrays;

/**
 * Класс отвечающий за команду пересылки сообщения от администратора пользователю указанному в первом аргументе.
 */
public class SendCommand extends AbsUserOperationCommand{

    public SendCommand(VerUserBot verUsersGroup, NoVerUserBot noVerUsersGroup) {
        super("/del", "Remove user from bot.", verUsersGroup, noVerUsersGroup);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {

        if (userIsAdmin(user, chat)){
            String textMSG = Arrays.toString(strings);
            UserBot userBot = getUserBotByIdentifier(strings[0]);
            if (userBot != null) {
                sendMsg(absSender, textMSG, userBot.getChat());
            }
        }
    }

    /**
     * Поиск и получение пользователя или null из верифицированных пользователей.
     * @param identifier идентификатор пользователя.
     * @return UserBot верифицированный пользователь или null
     */
    private UserBot getUserBotByIdentifier(String identifier){
        return mVerUsersGroup.searchUserBot(identifier);
    }
}
