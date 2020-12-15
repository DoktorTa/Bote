package Tasks;

import DataBase.MSSQLTaskTable;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс ответственен за создание задания.
 * Любое задание есть словарь.
 */
public class TaskRepository implements ITaskRepository {

    public final MSSQLTaskTable tableTasks;

    public TaskRepository(MSSQLTaskTable tableTask){
        tableTasks = tableTask;
    }

    @Override
    public String getTaskByNumber(String numberTask) {
        ArrayList<String> task = tableTasks.getTaskByNumber(numberTask);
        StringBuilder answer = new StringBuilder();

        if (!task.isEmpty()) {
            answer.append("Number: ").append(task.get(0))
                    .append(" Level: ").append(task.get(1))
                    .append(" Points: ").append(task.get(2)).append("\n")
                    .append(task.get(3).trim()).append("\n");

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

        tasksString.append("Уровень всех показанных заданий: ").append(levelTasks).append("\n");

        for (String task : allTasks) {
            tasksString.append("/").append(task).append("\n");
        }

        return tasksString.toString();    }

    @Override
    public String getAllTasks() {
        ArrayList<String> allTasks = tableTasks.getAllTasks();
        StringBuilder allTasksString = new StringBuilder();

        for (String allTask : allTasks) {
            allTasksString.append("/").append(allTask).append("\n");
        }

        return allTasksString.toString();
    }

    @Override
    public String getAnswerTask(String num) {
        return tableTasks.getAnswerTask(num);
    }

}
