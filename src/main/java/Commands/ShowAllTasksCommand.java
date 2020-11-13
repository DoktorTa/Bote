package Commands;

import Tasks.ITaskOperation;
import Users.IUsersOperation;
import Users.UserBot;

public class ShowAllTasksCommand extends AbsCommand{
    private final ITaskOperation taskOperation;

    public ShowAllTasksCommand(ITaskOperation iTaskOperation, IUsersOperation usersBotOperation) {
        super("/all_tasks", "Show all tasks", usersBotOperation);
        taskOperation = iTaskOperation;
    }

    @Override
    public String getAnswer(UserBot user, String[] strings) {
        return taskOperation.getAllTasks();
    }
}
