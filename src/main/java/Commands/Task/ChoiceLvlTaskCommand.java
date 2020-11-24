package Commands.Task;

import Commands.LastUserQuery;
import Tasks.ITaskRepository;
import Users.IUsersRepository;
import Users.UserBot;

public class ChoiceLvlTaskCommand extends AbstractTaskCommand{

    public ChoiceLvlTaskCommand(IUsersRepository usersBotOperation, ITaskRepository taskOperation, LastUserQuery lastUserQuery1) {
        super("/choice_lvl", "Choice level task/.", usersBotOperation, taskOperation, lastUserQuery1);
    }

    @Override
    public String getAnswer(UserBot user, String[] strings) {
        lastUserQuery.addQuery(user.getChatId(), "lvl" );
        return "1. Задания с выбором ответов.\n" +
                "2. Задания с полным ответом.\n" +
                "3. Задания посильные только человеку.\n";
    }
}
