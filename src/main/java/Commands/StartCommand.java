package Commands;

import Users.IUsersOperation;
import Users.UserBot;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;

public class StartCommand extends AbsCommand {

    public StartCommand(IUsersOperation usersBot){
        super("/start", usersBot);
    }

    private String getAnswer(User user, Chat chat){
        String textMSG;

        if (adminExistence()){
            return createAdmin(user, chat);
        }
        else if(usersBot.userInGroup(new UserBot(user, chat))){
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
        usersBot.addUserToNoVerGroup(userBot);
        return "Welcome, await administrator verification.";
    }

    /**
     * @return boolean создан админ.
     */
    private boolean adminExistence(){
        return usersBot.getAdmins() == null;
    }

    /**
     * Создает аккаунт администратора для первого пользователя нажавшего /start.
     * @param user пользователь телеграмма.
     * @param chat чат с пользователем.
     * @return String приветствие администратора.
     */
    private String createAdmin(User user, Chat chat){
        UserBot userBot = new UserBot(user, chat);
        usersBot.addAdmin(userBot);
        return "Hello my admin!";
    }

    @Override
    public String getAnswer() {
        return null;
    }
}
