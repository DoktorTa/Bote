package DataBase;

import java.sql.*;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBaseMSSQL implements IDataBase{
    private final Logger LOG;

    private static final String user = System.getenv("NAME_USER_DATABASE");
    private static final String password = System.getenv("PASSWORD_USER_DATABASE");
    private static final String url = "jdbc:sqlserver://127.0.0.1:1433;database=RetraceMSGBot;";
//            "user=" + user + ";" +
//            "password=" + password
//            + "encrypt=true;"
//            + "trustServerCertificate=false;"
//            + "loginTimeout=30;";

    private static Connection con;
    private static Statement stmt;

    public DataBaseMSSQL(Logger log){
        LOG = log;
    }

    @Override
    public void connectDataBase() throws ClassNotFoundException {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url, user, password);

            stmt = con.createStatement();


        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
//        finally {
//
//            try {
//                con.close();
//            } catch(SQLException se) { /*can't do anything */ }
//
//            try {
//                stmt.close();
//            } catch(SQLException se) { /*can't do anything */ }
//        }
    }

    @Override
    public ResultSet getTaskByLevel(String level) throws SQLException {
        String query = "SELECT * FROM Tasks WHERE LevelTask=" + level + ";";
        ResultSet rs = null;


        try {
            rs = stmt.executeQuery(query);
        } catch(SQLException se) {
            LOG.log(Level.WARNING, "Command get tasks by level is mistake. Level: " + level);
        }

        return rs;
    }

    @Override
    public boolean getTaskByNumber(String level) throws SQLException {
        String query = "";
        ResultSet rs = stmt.executeQuery(query);

        try {
            rs.close();
        } catch(SQLException se) { /*can't do anything */ }

        return false;
    }

    @Override
    public boolean setTask() throws SQLException {
        String query = "";
        ResultSet rs = stmt.executeQuery(query);

        try {
            rs.close();
        } catch(SQLException se) { /*can't do anything */ }

        return false;
    }

    @Override
    public boolean setAdmin() throws SQLException {
        String query = "";
        ResultSet rs = stmt.executeQuery(query);

        try {
            rs.close();
        } catch(SQLException se) { /*can't do anything */ }

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
