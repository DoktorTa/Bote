package Commands.Task;

import Commands.LastUserQuery;
import Tasks.ITaskRepository;
import Users.IUsersRepository;
import Users.UserBot;

public class GetTaskByLevelCommand extends AbstractTaskCommand {

    public GetTaskByLevelCommand(ITaskRepository iTaskRepository1, IUsersRepository usersBotOperation,
                                 LastUserQuery mLastUserQuery) {
        super("/lvl", "Get task by level.", usersBotOperation, iTaskRepository1, mLastUserQuery);
    }

    @Override
    public String getAnswer(UserBot user, String[] strings) {
        lastUserQuery.addQuery(user.getChatId(), "num" );
        return iTaskRepository.getTasksByLevel(strings[0]);
    }
}
