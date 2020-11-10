package Commands;

import Users.IUsersOperation;
import Users.UserBot;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.ArrayList;
import java.util.Arrays;

public class StartCommand extends AbsCommand {

    public StartCommand(IUsersOperation usersBot){
        super("/start", "Start command", usersBot);
    }

    /**
     * Получение текста ответного сообщения команды старт.
     * @param user пользователь телеграмма.
     * @return
     */
    @Override
    public String getAnswer(UserBot user, String[] strings){
        String textMSG;
        ArrayList<String> userFromVer = usersBot.getUserFromVerUser(user.identifier);

        if (adminExistence()){
            return createAdmin(user);
        }
        else if(userFromVer != null){
            return  "You are verified";
        }
        else {
            return addNoVerUser(user);
        }
    }

    /**
     *
     * @param user пользователь телеграмма.
     * @return String пользователь верифицирован.
     */
    private String addNoVerUser(UserBot user){
        usersBot.addUserToNoVerGroup(user);
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
