package Commands.Standart;

import Commands.AbstractCommand;
import Commands.LastUserQuery;
import Users.IUserRepository;
import Users.UserBot;

public class StartCommand extends AbstractCommand {

    public StartCommand(IUserRepository iUserRepository, LastUserQuery lastUserQuery){
        super("/start", "Start command", iUserRepository, lastUserQuery);
    }

    /**
     * Получение текста ответного сообщения команды старт.
     * @param user пользователь телеграмма.
     * @return текста ответного сообщения команды старт
     */
    @Override
    public String getAnswer(UserBot user, String[] strings){
        iUsersRepository.createUser(user.getIdentifier(), user.getChatId());
        lastUserQuery.addQuery(user.getChatId(), "help");

        String hello_string = "Привет, я бот который поможет тебе выяснить свои пробелы в знаниях программирования. \n" +
                "Если ты готов начать, то отправь любой символ.";
        return hello_string;
    }
}
