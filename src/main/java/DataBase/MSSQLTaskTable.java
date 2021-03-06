package DataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MSSQLTaskTable implements IDataBaseTask {
    private final Logger LOG;

    private static Statement stmt;

    public MSSQLTaskTable(Logger log, Statement statement){
        stmt = statement;
        LOG = log;
    }

    @Override
    public ArrayList<String> getTaskByLevel(String level){
        String query = "SELECT NumberTask, TextTask FROM Tasks WHERE LevelTask=" + level + ";";
        return getListTasks(query);
    }

    @Override
    public String getAnswerTask(String num) {
        StringBuilder answers = new StringBuilder();
        String query = "SELECT CorrectAnswer FROM Tasks WHERE NumberTask=" + num + ";";

        ResultSet resultSet = getQuerySELECTorNull(query);

        if (resultSet == null){
            return answers.toString();
        }

        try {
            resultSet.next();
            answers.append(resultSet.getString("CorrectAnswer").trim());
        } catch (SQLException se){
            LOG.log(Level.WARNING, query + " " + se.getMessage());
        }

        return answers.toString();
    }

    @Override
    public ArrayList<String> getTaskByNumber(String number){
        ArrayList<String> task = new ArrayList<String>();
        String query = "SELECT * FROM Tasks WHERE NumberTask=" + number + ";";

        ResultSet resultSet = getQuerySELECTorNull(query);

        if (resultSet == null){
            return task;
        }

        try {
            resultSet.next();
            task.add(resultSet.getString("NumberTask").trim());
            task.add(resultSet.getString("LevelTask").trim());
            task.add(resultSet.getString("Points").trim());
            task.add(resultSet.getString("TextTask").trim());
            task.add(resultSet.getString("TextAnswer").trim());
            task.add(resultSet.getString("CorrectAnswer").trim());
        } catch (SQLException se){
            LOG.log(Level.WARNING, query + " " + se.getMessage());
        }

        return task;
    }

    @Override
    public ArrayList<String> getAllTasks() {
        String query = "SELECT NumberTask, TextTask FROM Tasks;";
        return getListTasks(query);
    }


    /**
     * Метод для отправки запросов с типом SELECT.
     @return ResultSet - ответ на запрос, в случает ошибки возврат null.
     */
    private ResultSet getQuerySELECTorNull(String query){
        ResultSet resultSet = null;

        try {
            resultSet = stmt.executeQuery(query);
        } catch(SQLException se) {
            LOG.log(Level.WARNING, query + " " + se.getMessage());
        }

        return resultSet;
    }

    /**
     * Возвращает лист заданий, каждая строка выглядит как: "№. TextTask\n" длинна всей строки не более 51 символа.
     * @param query SQl запрос который вернет необходимую выборку с полями NumberTask, TextTask.
     * @return лист заданий.
     */
    private ArrayList<String> getListTasks(String query){
        int endString = 50;
        ArrayList<String> task = new ArrayList<String>();
        ResultSet resultSet = getQuerySELECTorNull(query);

        if (resultSet == null){
            return task;
        }

        try {
            while (resultSet.next()) {
                String numTask = resultSet.getString("NumberTask") + ". ";
                String textTask = resultSet.getString("TextTask");
                textTask = textTask.substring(0, endString - numTask.length());
                task.add(numTask + textTask);
            }
        } catch (SQLException se){
            LOG.log(Level.WARNING, query + " " + se.getMessage());
            se.printStackTrace();
        }

        return task;
    }

}
