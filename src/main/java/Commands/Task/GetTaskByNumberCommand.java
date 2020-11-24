package Commands.Task;

import Commands.LastUserQuery;
import Tasks.ITaskRepository;
import Users.IUsersRepository;
import Users.UserBot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetTaskByNumberCommand extends AbstractTaskCommand {

    public GetTaskByNumberCommand(ITaskRepository iTaskRepository1, IUsersRepository usersBotOperation,
                                  LastUserQuery mLastUserQuery) {
        super("/num", "Get task by number", usersBotOperation, iTaskRepository1, mLastUserQuery);
    }

    @Override
    public String getAnswer(UserBot user, String[] strings) {
        String taskInfo = iTaskRepository.getTaskByNumber(strings[0]);
        lastUserQuery.addQuery(user.getChatId(), "answer " + strings[0]);

        taskInfo = addPostScript(taskInfo);

        return taskInfo;
    }

    private String addPostScript(String taskInfo){
        Pattern pattern = Pattern.compile("(?<=Number: )([0-9]*)");
        Matcher matcher = pattern.matcher(taskInfo);

        while (matcher.find()) {
            String levelTask = taskInfo.substring(matcher.start(), matcher.end());
            if (levelTask.equals("1")){
                taskInfo += "\n \nP.S. Если ответов несколько то вводить их через пробел.";
            }
        }

        return taskInfo;
    }
}
