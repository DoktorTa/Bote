package DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
    Класс для работы с базой данных Microsoft SQL server.
 */
public class DataBaseMSSQL implements IDataBase{
    private final Logger LOG;

    private static final String user = System.getenv("NAME_USER_DATABASE");
    private static final String password = System.getenv("PASSWORD_USER_DATABASE");
    private static final String url = "jdbc:sqlserver://127.0.0.1:1433;database=RetraceMSGBot;";

    private static Connection con;
    private static Statement stmt;

    public DataBaseMSSQL(Logger log){
        LOG = log;
    }

    /**
        Метод создает соединение с базой данных.
     */
    @Override
    public void connectDataBase(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
        } catch (SQLException | ClassNotFoundException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    //TODO: Данный метод нарушает SRP, но с другой стороны он связывает несколько ответственностей в одну, действуя как
    // команда.
    /**
        Метод для получения заданий по их уровню.
        @param level уровень заданий.
        @return список номеров и текстов заданий.
     */
    @Override
    public ArrayList<String> getTaskByLevel(String level){
        ArrayList<String> tasks = new ArrayList<String>();
        String query = "SELECT * FROM Tasks WHERE LevelTask=" + level + ";";

        ResultSet resultSet = getQuerySELECTorNull(query);

        if (resultSet == null){
            return tasks;
        }


        try{
            while (resultSet.next()){
                String taskLine =
                        resultSet.getString("NumberTask") + ": " + resultSet.getString("TextTask");
                tasks.add(taskLine);
            }
        } catch (SQLException se){
            LOG.log(Level.WARNING, query + " " + se.getMessage());
        }

        return tasks;
    }

    //TODO: Данный метод нарушает SRP, но с другой стороны он связывает несколько ответственностей в одну, действуя как
    // команда.
    /**
        Метод для получения задания по его номеру.
     @return список одного задания состоящий из
        номера задания, уровня задания, баллов за задание, текста задания, вариантов ответа, правильного ответа.
      * @param number номер заданий.
     */
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
            task.add(resultSet.getString("NumberTask"));
            task.add(resultSet.getString("LevelTask"));
            task.add(resultSet.getString("Points"));
            task.add(resultSet.getString("TextTask"));
            task.add(resultSet.getString("TextAnswer"));
            task.add(resultSet.getString("CorrectAnswer"));
        } catch (SQLException se){
            LOG.log(Level.WARNING, query + " " + se.getMessage());
            se.printStackTrace();
        }
        return task;
    }

    /**
        Метод который создает новое задание.
        @param numberTask номер задания.
        @param levelTask уровень задания.
        @param points количество баллов за задание.
        @param textTask текст задания.
        @param textAnswer текст вариантов ответа на задание.
        @param correctAnswer правильные ответы.
        @return false если во время выполнения запроса произошла ошибка.
     */
    @Override
    public boolean setTask(String numberTask, String levelTask, String points,
                           String textTask, String textAnswer, String correctAnswer){
        String query = "INSERT INTO Tasks " + "VALUES (" +
                numberTask + ", " +
                levelTask + ", " +
                points + ", '" +
                textTask + "', '" +
                textAnswer + "', '" +
                correctAnswer + "')";
        return getQueryINSERT_DELETE_UPDATE(query);
    }

    /**
        Метод удаляющий задание по его номеру.
        @param numberTask номер задания.
        @return false если во время удаления произошла ошибка.
     */
    @Override
    public boolean removeTask(String numberTask) {
        String query = "DELETE FROM Tasks WHERE NumberTask=" + numberTask + ";";
        return getQueryINSERT_DELETE_UPDATE(query);
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
     Метод для отправки запросов типа INSERT, DELETE, UPDATE.
     @return false если во время выполнения запроса возникла ошибка.
     */
    private boolean getQueryINSERT_DELETE_UPDATE(String query){
        try {
            stmt.executeUpdate(query);
            return true;
        } catch (SQLException se){
            LOG.log(Level.WARNING, query + " " + se.getMessage());
            return false;
        }
    }

    @Override
    public boolean getAdmin() {
        return false;
    }

    @Override
    public boolean getVerificationUsers() throws SQLException {
        return false;
    }

    @Override
    public boolean setAdmin() throws SQLException {
        String query = "";
        boolean rs = stmt.execute(query);

//        try {
//            rs.close();
//        } catch(SQLException se) { /*can't do anything */ }

        return false;
    }

    @Override
    public boolean setVerificationUsers() throws SQLException {
        String query = "";
        ResultSet rs = stmt.executeQuery(query);

        try {
            rs.close();
        } catch(SQLException se) { /*can't do anything */ }

        return false;
    }
}
