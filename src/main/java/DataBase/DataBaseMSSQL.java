package DataBase;

import java.sql.*;
import java.util.logging.Logger;

/**
    Класс для работы с базой данных Microsoft SQL server.
 */
public class DataBaseMSSQL{
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
    public Statement connectDataBase(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
        } catch (SQLException | ClassNotFoundException sqlEx) {
            sqlEx.printStackTrace();
        }
        return stmt;
    }
}
