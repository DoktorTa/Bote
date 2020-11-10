package DataBase;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IDataBase{

    void connectDataBase();

    ArrayList<String> getTaskByLevel(String level);

    ArrayList<String> getTaskByNumber(String level);

    boolean setTask(String numberTask, String levelTask, String points,
                    String textTask, String textAnswer, String correctAnswer);

    boolean removeTask(String numberTask);

    boolean getAdmin();

    boolean getVerificationUsers() throws SQLException;

    boolean setAdmin() throws SQLException;

    boolean setVerificationUsers() throws SQLException;

}
