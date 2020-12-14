package DataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MSSQLUserTable implements IDataBaseUser {
    private final Logger LOG;

    private static Statement stmt;

    public MSSQLUserTable(Logger log, Statement statement){
        stmt = statement;
        LOG = log;
    }

    @Override
    public void addUserToDataBase(String identifier, String chatId, int points, int taskComplete) {
        String query = "INSERT INTO Users " + "VALUES (" +
                identifier + ", " +
                taskComplete + ", " +
                points + ", "+
                chatId + ")";
        getQueryINSERT_DELETE_UPDATE(query);
    }

    @Override
    public void removeUserToDataBase(String identifier) {
        String query = "DELETE FROM Users WHERE IdentifierUser='" + identifier + "';";
        getQueryINSERT_DELETE_UPDATE(query);
    }

    @Override
    public String getAdminIdentifier() {
        String query = "SELECT * FROM Users WHERE IsAdmin=1;";
        ArrayList<String> fieldsAnswer = new ArrayList<>();
        fieldsAnswer.add("IdentifierUser");
        return getResultRequestOrNull(fieldsAnswer, query).get(0);
    }

    @Override
    public int getAllCompleteTaskUser(String identifier) {
        return 0;
    }

    @Override
    public ArrayList<String> getUserToDataBase(String identifier) {
        String query = "SELECT * FROM Users WHERE IdentifierUser='" + identifier + "';";
        ArrayList<String> fieldsAnswer = new ArrayList<>();
        fieldsAnswer.add("IdentifierUser");
        fieldsAnswer.add("IsAdmin");
        fieldsAnswer.add("ChatId");
        return getResultRequestOrNull(fieldsAnswer, query);
    }

    /**
     * Разбирает результат SQL запроса с помощью списка необходимых полей, работает только с первым найденным элементом.
     * @param fields список строк с названиями необходимых полей.
     * @param query текст запроса, необходим для логирования.
     * @return список с необходимыми результатами в порядке списка полей.
     */
    private ArrayList<String> getResultRequestOrNull(ArrayList<String> fields, String query){
        ArrayList<String> identifier = new ArrayList<>();
        ResultSet resultSet = getQuerySELECTorNull(query);

        if (resultSet == null){
            return new ArrayList<>();
        }

        try{
            if (resultSet.next()){
                for (String field : fields) {
                    String fieldContent = resultSet.getString(field).trim();
                    identifier.add(fieldContent);
                }
            }
        } catch (SQLException se){
            identifier = new ArrayList<>();
            LOG.log(Level.WARNING, query + " " + se.getMessage());
            se.printStackTrace();
        }

        return identifier;
    }

    /**
     Метод для отправки запросов типа INSERT, DELETE, UPDATE.
     */
    private void getQueryINSERT_DELETE_UPDATE(String query){
        try {
            stmt.executeUpdate(query);
        } catch (SQLException se){
            LOG.log(Level.WARNING, query + " " + se.getMessage());
        }
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

}
