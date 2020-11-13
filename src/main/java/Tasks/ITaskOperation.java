package Tasks;

public interface ITaskOperation {

    public String createTask(String levelTask, String points, String textTask, String textAnswer, String correctAnswer);

    public String removeTaskByNumber(String numberTask);

    public String getTaskByNumber(String numberTask);

    public String getTasksByLevel(String levelTasks);

    public String getAllTasks();
}
