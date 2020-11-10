package DataBase;

import java.util.ArrayList;

public interface IDataBaseTask {

    ArrayList<String> getTaskByLevel(String level);

    ArrayList<String> getTaskByNumber(String level);

    boolean setTask(String numberTask, String levelTask, String points,
                    String textTask, String textAnswer, String correctAnswer);

    boolean removeTask(String numberTask);
}