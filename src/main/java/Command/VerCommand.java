package Command;

import Users.NoVerUserBot;
import Users.UserBot;
import Users.VerUserBot;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

/**
 * Класс ответственный за команду верификации.
 */
public class VerCommand extends AbsUserOperationCommand {

    public VerCommand(VerUserBot verUsersGroup, NoVerUserBot noVerUsersGroup) {
        super("/ver", "Verifies the user by the identifier, if he is in the list of unverified users.", verUsersGroup, noVerUsersGroup);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String textMSGToAdmin = "";

        if (userIsAdmin(user, chat)){
            UserBot userBot = deleteNoVerUser(strings[0]);

            if (userBot == null){
                textMSGToAdmin = "User not found!";
            }
            else {
                textMSGToAdmin = verificationUser(userBot);
                if (textMSGToAdmin == null){
                    return;
                }
                sendMsg(absSender, "You verification!", userBot.getChat());
            }

            sendMsg(absSender, textMSGToAdmin, chat);
        }
    }

    /**
     * Удаление из списка не верифицированных пользователей.
     * @param identifier идентификатор пользователя.
     * @return UserBot or null. Удаленный пользователь.
     */
    private UserBot deleteNoVerUser(String identifier){
        UserBot userBot = mNoVerUsersGroup.searchUserBot(identifier);

        if (userBot != null){
            mNoVerUsersGroup.removeUserBot(userBot.getUser());
            return userBot;
        }

        return null;
    }

    /**
     * Добавление пользователя в список верифицированных.
     * @param userBot пользователь.
     * @return String текст успешной верификации.
     */
    private String verificationUser(UserBot userBot){
        mVerUsersGroup.addUserBot(userBot);
        return "User " + userBot.identifier + " verification!";
    }
}
