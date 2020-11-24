package Commands.Task;

import Commands.AbstractCommand;
import Tasks.ITaskRepository;
import Users.IUsersRepository;
import Users.UserBot;

public class RemoveTaskCommand extends AbstractCommand {
    private final ITaskRepository taskOperation;

    public RemoveTaskCommand(ITaskRepository iTaskRepository, IUsersRepository usersBotOperation) {
        super("/remove_task", "Delete task.", usersBotOperation);
        taskOperation = iTaskRepository;
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
