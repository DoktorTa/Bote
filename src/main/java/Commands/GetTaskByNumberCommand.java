package Commands;

import Tasks.ITaskOperation;
import Users.IUsersOperation;
import Users.UserBot;

public class GetTaskByNumberCommand extends AbsCommand{
    private final ITaskOperation taskOperation;

    public GetTaskByNumberCommand(ITaskOperation iTaskOperation, IUsersOperation usersBotOperation) {
        super("/num", "Get task by number", usersBotOperation);
         taskOperation = iTaskOperation;
    }

    @Override
    public String getAnswer(UserBot user, String[] strings) {
        String taskAnswer = taskOperation.getTaskByNumber(strings[0]);
        return taskAnswer;
    }
}
