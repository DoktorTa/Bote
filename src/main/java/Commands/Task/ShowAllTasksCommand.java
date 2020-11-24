package Commands.Task;

import Commands.LastUserQuery;
import Tasks.ITaskRepository;
import Users.IUsersRepository;
import Users.UserBot;

public class ShowAllTasksCommand extends AbstractTaskCommand {

    public ShowAllTasksCommand(ITaskRepository taskOperation, IUsersRepository usersBotOperation,
                               LastUserQuery lastUserQuery1) {
        super("/all_tasks", "Show all tasks",
                usersBotOperation, taskOperation, lastUserQuery1);
    }

    @Override
    public String getAnswer(UserBot user, String[] strings) {
        lastUserQuery.addQuery(user.getChatId(), "num");
        return iTaskRepository.getAllTasks();
    }
}
