package Commands;

import Tasks.ITaskOperation;
import Users.IUsersOperation;
import Users.UserBot;

import java.util.ArrayList;

public class CreateTaskCommand extends AbsCommand{
    private final ITaskOperation taskOperation;

    public CreateTaskCommand(ITaskOperation iTaskOperation, IUsersOperation usersBotOperation) {
        super("/create_task", "Create new task.", usersBotOperation);
        taskOperation = iTaskOperation;
    }

    @Override
    public String getAnswer(UserBot user, String[] strings) {
        String answer = "";
        if (userIsAdmin(user.getIdentifier())) {
            ArrayList<String> fieldsTask = getFieldsTask(strings);
            String levelTask = fieldsTask.get(0);
            String points = fieldsTask.get(1);
            String textTask = fieldsTask.get(2);
            String textAnswer = fieldsTask.get(3);
            String correctAnswer = fieldsTask.get(4);

            answer = taskOperation.createTask(levelTask, points, textTask, textAnswer, correctAnswer);
        }
        return answer;
    }

    private ArrayList<String> getFieldsTask(String[] strings){
        ArrayList<String> fieldsTask = new ArrayList<String>();
        String field = "";
        String delimiter = ">^<";

        for (String word: strings) {

            if (word.equals(delimiter)){
                fieldsTask.add(field);
                field = "";
                if (fieldsTask.size() == 5){
                    break;
                }
            } else {
                field += word + " ";
            }
        }

        return fieldsTask;
    }
}
