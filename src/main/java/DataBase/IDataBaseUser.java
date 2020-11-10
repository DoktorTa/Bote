package DataBase;

import Users.UserBot;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IDataBaseUser {
    boolean setUser(String identifier, String isAdmin, String chatId);

    boolean removeUser(String identifier);

    ArrayList<String> getUser(String identifier);

    String getAdminIdentifier();
}
