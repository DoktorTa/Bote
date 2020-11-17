package Commands;

import Users.IUsersOperation;
import Users.UserBot;

import java.util.ArrayList;

public class StartCommand extends AbsCommand {

    public StartCommand(IUsersOperation usersBot){
        super("/start", "Start command", usersBot);
    }

    /**
     * Получение текста ответного сообщения команды старт.
     * @param user пользователь телеграмма.
     * @return текста ответного сообщения команды старт
     */
    @Override
    public String getAnswer(UserBot user, String[] strings){

        if (adminExistence()){
            return createAdmin(user);
        }

        ArrayList<String> userFromVer = usersBot.getUserFromVerifiedUsers(user.getIdentifier());
        if(userFromVer != null){
            return  "You are verified";
        } else {
            return addNoVerUser(user);
        }
    }

    /**
     *
     * @param user пользователь телеграмма.
     * @return String пользователь верифицирован.
     */
    private String addNoVerUser(UserBot user){
        usersBot.addUserToNoVerifiedUsers(user);
        return "Welcome, await administrator verification.";
    }

    /**
     * @return boolean создан админ.
     */
    private boolean adminExistence(){
        return usersBot.getAdminIdentifier() == null;
    }

    /**
     * Создает аккаунт администратора для первого пользователя нажавшего /start.
     * @param user пользователь телеграмма.
     * @return String приветствие администратора.
     */
    private String createAdmin(UserBot user){
        usersBot.addAdmin(user);
        return "Hello my admin!";
    }

}
