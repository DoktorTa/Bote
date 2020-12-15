package Commands.Standart;

import Commands.AbstractCommand;
import Commands.LastUserQuery;
import Users.IUserRepository;
import Users.UserBot;

public class HelpCommand extends AbstractCommand {

    public HelpCommand(IUserRepository iUserRepository, LastUserQuery lastUserQuery) {
        super("/help", "help command", iUserRepository, lastUserQuery);
    }

    @Override
    public String getAnswer(UserBot user, String[] strings) {
        return userCommandList(new StringBuilder());
    }

    private String userCommandList(StringBuilder help){
        help.append("Бот создан для того чтобы вы могли проверить свои знания хоть где-нибудь.\n");
        help.append("\n Вы можете начать прямо сейчас выбрав из списка заданий: /all_tasks \n");
//        help.append("Если вы хотите выбрать задания определенного уровня: /choice_level \n");
//        help.append("Виду того что ответ на некоторые задания нельзя проверить с помощью компьютера, ответы на вопросы " +
//                "без выбора ответа стоит вводить повторно в данной ботом форме, для того чтобы они были засчитаны.");
        help.append("\n\n\n\n Наш слоган:\n Мы не морские пехотинцы, поэтому своих можно и бросить.");
        return help.toString();
    }
}
