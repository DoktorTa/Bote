package Command;

import Users.NoVerUserBot;
import Users.UserBot;
import Users.VerUserBot;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

/**
 * Класс ответственный за команду создания внутреннего пользователя бота, а так же за запрос верификации.
 */
public class StartCommand extends AbsUserOperationCommand{

    public StartCommand(VerUserBot verUsersGroup, NoVerUserBot noVerUsersGroup) {
        super("/start", "Starting retrace msg to admin.", verUsersGroup, noVerUsersGroup);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings){
        sendMsg(absSender, getTextMSG(user, chat), chat);
    }

    /**
     * Получение текста ответного сообщения команды старт.
     * @param user пользователь телеграмма.
     * @param chat чат с пользователем.
     * @return String текст ответа.
     */
    private String getTextMSG(User user, Chat chat){
        String textMSG;

        if (adminExistence()){
            return createAdmin(user, chat);
        }
        else if(mVerUsersGroup.userInGroup(new UserBot(user, chat))){
            return  "You are verified";
        }
        else {
            return addNoVerUser(user, chat);
        }
    }

    /**
     *
     * @param user пользователь телеграмма.
     * @param chat чат с пользователем.
     * @return String пользователь верифицирован.
     */
    private String addNoVerUser(User user, Chat chat){
        UserBot userBot = new UserBot(user, chat);
        mNoVerUsersGroup.addUserBot(userBot);
        return "Welcome, await administrator verification.";
    }

    /**
     * @return boolean создан админ.
     */
    private boolean adminExistence(){
        return mVerUsersGroup.getAdmin() == null;
    }

    /**
     * Создает аккаунт администратора для первого пользователя нажавшего /start.
     * @param user пользователь телеграмма.
     * @param chat чат с пользователем.
     * @return String приветствие администратора.
     */
    private String createAdmin(User user, Chat chat){
        UserBot userBot = new UserBot(user, chat);
        mVerUsersGroup.setAdmin(userBot);
        return "Hello my admin!";
    }
}
