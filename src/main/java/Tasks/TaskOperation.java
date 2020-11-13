package Tasks;

import DataBase.MSSQLTaskTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс ответственен за создание задания.
 * Любое задание есть словарь.
 */
public class TaskOperation implements ITaskOperation {

    public final MSSQLTaskTable tableTasks;

    public TaskOperation(MSSQLTaskTable tableTask){
        tableTasks = tableTask;
    }

    private String getNumberNextTask(ArrayList<Integer> allNumber){
        Integer inc = 1;

        while (allNumber.contains(inc)){
            inc++;
        }

        return inc.toString();
    }

    @Override
    public String createTask(String levelTask, String points, String textTask, String textAnswer, String correctAnswer) {
        String numTask = getNumberNextTask(tableTasks.getTaskAllNumber());

        if (tableTasks.setTask(numTask, levelTask, points, textTask, textAnswer, correctAnswer)){
            return "Task create, number: " + numTask;
        } else {
            return "Error task create!";
        }
    }

    @Override
    public String removeTaskByNumber(String numberTask) {
        String answer = "";

        if (tableTasks.removeTask(numberTask)){
            answer = "Task delete, number " + numberTask + " is clear";
        } else {
            answer = "Task not delete, number: " + numberTask;
        }

        return answer;
    }

    @Override
    public String getTaskByNumber(String numberTask) {
        ArrayList<String> task = tableTasks.getTaskByNumber(numberTask);
        StringBuilder answer = new StringBuilder();

        if (!task.isEmpty()) {
            answer.append("Number: ").append(task.get(0))
                    .append(" Level: ").append(task.get(1))
                    .append(" Points: ").append(task.get(2)).append("\n")
                    .append(task.get(3)).append("\n");

            String taskTextAnswer = task.get(4);
            Pattern pattern = Pattern.compile("([0-9]+[.][\\s])(.*?)(?=([0-9]+[.][\\s])|$)");
            Matcher matcher = pattern.matcher(taskTextAnswer);

            while (matcher.find()){
                answer.append("\n    ").append(taskTextAnswer, matcher.start(), matcher.end());
            }
        } else {
            answer.append("Task is dont exist.");
        }

        return answer.toString();
    }

    @Override
    public String getTasksByLevel(String levelTasks) {
        ArrayList<String> allTasks = tableTasks.getTaskByLevel(levelTasks);
        StringBuilder tasksString = new StringBuilder();

        tasksString.append("Level all task this table: ").append(levelTasks).append("\n");

        for (int inc = 0; allTasks.size() > inc; inc++){
            tasksString.append(allTasks.get(inc)).append("\n");
        }

        return tasksString.toString();    }

    @Override
    public String getAllTasks() {
        ArrayList<String> allTasks = tableTasks.getAllTasks();
        StringBuilder allTasksString = new StringBuilder();

        for (int inc = 0; allTasks.size() > inc; inc++){
            allTasksString.append(allTasks.get(inc)).append("\n");
        }

        return allTasksString.toString();
    }
}
