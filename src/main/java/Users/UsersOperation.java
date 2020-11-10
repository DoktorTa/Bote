package Users;

import DataBase.MSSQLUserTable;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.ArrayList;

public class UsersOperation implements IUsersOperation {
    private final VerUserBot verUserBot;
    private final NoVerUserBot noVerUserBot;

    private final MSSQLUserTable usersTable;


    public UsersOperation(MSSQLUserTable mssqlUserTable){
        usersTable = mssqlUserTable;
        verUserBot = new VerUserBot();
        noVerUserBot = new NoVerUserBot();
    }



    @Override
    public void addAdmin(UserBot newAdmin) {
        usersTable.setUser(newAdmin.identifier, "1", newAdmin.getChat().getId().toString());
    }

    @Override
    public void addUserToVerGroup(UserBot user) {
        usersTable.setUser(user.identifier, "0", user.getChat().getId().toString());
    }

    @Override
    public String getAdminIdentifier() {
        return usersTable.getAdminIdentifier();
    }

    @Override
    public ArrayList<String> getUserFromVerUser(String identifier) {
        return usersTable.getUser(identifier);
    }

    @Override
    public void addUserToNoVerGroup(UserBot user) {
        noVerUserBot.addUserBot(user);
    }

    @Override
    public UserBot searchUserInNoVerGroup(String identifier) {
        return noVerUserBot.searchUserBot(identifier);
    }

    @Override
    public String getUsersGroupString() {
        return noVerUserBot.getUsersGroupString();
    }

    @Override
    public void removeUserToNoVerGroup(User user) {
        noVerUserBot.removeUserBot(user);
    }
}
