package Commands.Task;

import Commands.LastUserQuery;
import Tasks.ITaskRepository;
import Users.IUserRepository;
import Users.UserBot;

public class ChoiceLvlTaskCommand extends AbstractTaskCommand{

    public ChoiceLvlTaskCommand(IUserRepository iUserRepository, ITaskRepository taskOperation, LastUserQuery lastUserQuery1) {
        super("/choice_level", "Choice level task/.", iUserRepository,
                taskOperation, lastUserQuery1);
    }

    @Override
    public String getAnswer(UserBot user, String[] strings) {
        lastUserQuery.addQuery(user.getChatId(), "lvl" );
        StringBuilder answer = new StringBuilder();
        answer.append("/1 уровень: Задания с выбором ответов, как правило более легкие.\n");
        answer.append("/2 уровень: Задания с кратким ответом.\n");
        answer.append("/3 уровень: Задания наибольшей сложности, правильные ответы будут держаться в тайне," +
                " предполагают под собой написание кода.\n\n");
        return answer.toString();
    }
}
