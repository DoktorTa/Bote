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
