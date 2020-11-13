package Commands;

import Tasks.ITaskOperation;
import Users.IUsersOperation;
import Users.UserBot;

public class GetTaskByLevelCommand extends AbsCommand{
    private final ITaskOperation taskOperation;

    public GetTaskByLevelCommand(ITaskOperation iTaskOperation, IUsersOperation usersBotOperation) {
        super("/lvl", "Get task by level.", usersBotOperation);
        taskOperation = iTaskOperation;
    }

    @Override
    public String getAnswer(UserBot user, String[] strings) {
        return taskOperation.getTasksByLevel(strings[0]);
    }
}
