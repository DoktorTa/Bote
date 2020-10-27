package Command;

import Users.NoVerUserBot;
import Users.UserBot;
import Users.VerUserBot;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

/**
 * Команда удаления пользователя из списка пользователей ожидающих верификации.
 */
public class DelCommand extends AbsUserOperationCommand {

    public DelCommand(VerUserBot verUsersGroup, NoVerUserBot noVerUsersGroup) {
        super("/del", "Remove user from bot.", verUsersGroup, noVerUsersGroup);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {

        if (userIsAdmin(user, chat)){
            String textMSG = delInNoVerGroup(strings[0]);
            sendMsg(absSender, textMSG, chat);
        }
    }

    /**
     * @param identifier идентификатор пользователя.
     * @return String текст сообщения для отправки пользователю.
     */
    private String delInNoVerGroup(String identifier){
        UserBot userBot = mNoVerUsersGroup.searchUserBot(identifier);

        if (userBot != null){
            mNoVerUsersGroup.removeUserBot(userBot.getUser());
            return "User " + identifier + " deleted!";
        }
        return "User " + identifier + " not found in not verification list!";
    }

}
