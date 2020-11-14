package Commands;

import Tasks.ITaskOperation;
import Users.IUsersOperation;
import Users.UserBot;

public class RemoveTaskCommand extends AbsCommand{
    private final ITaskOperation taskOperation;

    public RemoveTaskCommand(ITaskOperation iTaskOperation, IUsersOperation usersBotOperation) {
        super("/remove_task", "Delete task.", usersBotOperation);
        taskOperation = iTaskOperation;
    }

    @Override
    public String getAnswer(UserBot user, String[] strings) {
        String answer = "";

        if (userIsAdmin(user.getIdentifier())){
            answer = taskOperation.removeTaskByNumber(strings[0]);
        }

        return answer;
    }
}
