package DataBase;

import Users.UserBot;

import java.sql.Connection;
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
    public boolean setUser(String identifier, String isAdmin, String chatId) {
        String query = "INSERT INTO Users " + "VALUES ('" +
                identifier + "', " +
                isAdmin + ", '" +
                chatId + "')";
        return getQueryINSERT_DELETE_UPDATE(query);
    }

    @Override
    public boolean removeUser(String identifier) {
        String query = "DELETE FROM Users WHERE Identifier=\"" + identifier + "\";";
        return getQueryINSERT_DELETE_UPDATE(query);
    }

    @Override
    public String getAdminIdentifier() {
        String identifier = null;
        String query = "SELECT * FROM Users WHERE IsAdmin=1;";

        ResultSet resultSet = getQuerySELECTorNull(query);

        if (resultSet == null){
            return null;
        }

        try{
            resultSet.next();
            identifier = resultSet.getString("Identifier");
        } catch (SQLException se){
            LOG.log(Level.WARNING, query + " " + se.getMessage());
        }

        return identifier;
    }

    @Override
    public ArrayList<String> getUser(String identifier) {
        ArrayList<String> user = null;
        String query = "SELECT * FROM Users WHERE Identifier=" + identifier + ";";

        ResultSet resultSet = getQuerySELECTorNull(query);

        if (resultSet == null){
            return null;
        }

        try{
            resultSet.next();
            user.add(resultSet.getString("Identifier"));
            user.add(resultSet.getString("IsAdmin"));
            user.add(resultSet.getString("ChatId"));

        } catch (SQLException se){
            LOG.log(Level.WARNING, query + " " + se.getMessage());
            user = null;
        }

        return user;
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
