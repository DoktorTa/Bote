package Commands.Task;

import Commands.LastUserQuery;
import Tasks.ITaskRepository;
import Users.IUserRepository;
import Users.UserBot;

public class GetTaskByLevelCommand extends AbstractTaskCommand {

    public GetTaskByLevelCommand(IUserRepository iUserRepository,
                                 ITaskRepository iTaskRepository1, LastUserQuery mLastUserQuery) {
        super("/lvl", "Get task by level.",
                iUserRepository, iTaskRepository1, mLastUserQuery);
    }

    @Override
    public String getAnswer(UserBot user, String[] strings) {
        lastUserQuery.addQuery(user.getChatId(), "num" );
        return iTaskRepository.getTasksByLevel(strings[0]);
    }
}
