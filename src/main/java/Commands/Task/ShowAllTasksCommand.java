package Commands.Task;

import Commands.LastUserQuery;
import Tasks.ITaskRepository;
import Users.IUserRepository;
import Users.UserBot;

public class ShowAllTasksCommand extends AbstractTaskCommand {

    public ShowAllTasksCommand(IUserRepository iUserRepository,
                               ITaskRepository taskOperation, LastUserQuery lastUserQuery1) {
        super("/all_tasks", "Show all tasks", iUserRepository,
                taskOperation, lastUserQuery1);
    }

    @Override
    public String getAnswer(UserBot user, String[] strings) {
        lastUserQuery.addQuery(user.getChatId(), "num");
        return iTaskRepository.getAllTasks();
    }


}

/*
* 1. Интерфейс Map в java реализуют:
2. Как проверить принадлежность к классу
3. Версии Java обладающие LTS
4. Как зовут создателя языка python
5. Напишите симфонию.
* */
