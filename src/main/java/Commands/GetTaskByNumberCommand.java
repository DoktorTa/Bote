package Commands;

import Tasks.ITaskRepository;
import Users.IUsersOperation;
import Users.UserBot;

public class GetTaskByNumberCommand extends AbstractTaskCommand {

    public GetTaskByNumberCommand(ITaskRepository iTaskRepository1, IUsersOperation usersBotOperation,
                                  LastUserQuery mLastUserQuery) {
        super("/num", "Get task by number", usersBotOperation, iTaskRepository1, mLastUserQuery);
    }

    @Override
    public String getAnswer(UserBot user, String[] strings) {
        String taskAnswer = iTaskRepository.getTaskByNumber(strings[0]);
        lastUserQuery.addQuery(user.getChatId(), "answer " + strings[0]);
        return taskAnswer;
    }
}
