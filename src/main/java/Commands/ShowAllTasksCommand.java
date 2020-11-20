package Commands;

import Tasks.ITaskRepository;
import Users.IUsersOperation;
import Users.UserBot;

public class ShowAllTasksCommand extends AbstractTaskCommand {

    public ShowAllTasksCommand(ITaskRepository taskOperation, IUsersOperation usersBotOperation,
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
