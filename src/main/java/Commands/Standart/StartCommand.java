package Commands.Standart;

import Commands.AbstractCommand;
import Users.IUsersRepository;
import Users.UserBot;

import java.util.ArrayList;

public class StartCommand extends AbstractCommand {

    public StartCommand(IUsersRepository usersBot){
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
        } else if(!userVerified(user.getIdentifier())){
            return  "You are verified";
        } else {
            return addNoVerUser(user);
        }
    }

    /**
     * @return boolean создан админ.
     */
    private boolean adminExistence(){
        return usersBot.getAdminIdentifier() == null;
    }

    /**
     * Проверяет проверен ли пользователь до этого.
     * @param identifier идентификатор пользователя.
     * @return проверен ли пользователь до этого.
     */
    private boolean userVerified(String identifier){
        ArrayList<String> userFromVer = usersBot.getUserFromVerifiedUsers(identifier);
        return userFromVer.isEmpty();
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
     * Создает аккаунт администратора для первого пользователя нажавшего /start.
     * @param user пользователь телеграмма.
     * @return String приветствие администратора.
     */
    private String createAdmin(UserBot user){
        usersBot.addAdmin(user);
        return "Hello my admin!";
    }

}
