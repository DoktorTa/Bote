package DataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public interface IDataBase{

    void connectDataBase() throws ClassNotFoundException;

    ResultSet getTaskByLevel(String level) throws SQLException;

    boolean getTaskByNumber(String level) throws SQLException;

    boolean setTask() throws SQLException;

    boolean setAdmin() throws SQLException;

    boolean setVerificationUsers() throws SQLException;

}
