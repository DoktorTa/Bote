package Users;

import DataBase.IDataBaseUser;

import java.util.ArrayList;

public class UsersRepository implements IUsersRepository {
    private final NoVerifiedUsers noVerUsersGroup;

    // Можно обобщить и убрать базу данных в принципе.
    private final IDataBaseUser usersTable;

    public UsersRepository(IDataBaseUser mssqlUserTable){
        usersTable = mssqlUserTable;
        noVerUsersGroup = new NoVerifiedUsers();
    }

    @Override
    public void addAdmin(UserBot newAdmin) {
        usersTable.addUserToDataBase(newAdmin.getIdentifier(), "1", newAdmin.getChatId());
    }

    @Override
    public void addUserToVerifiedUsers(UserBot user) {
        usersTable.addUserToDataBase(user.getIdentifier(), "0", user.getChatId());
    }

    @Override
    public void removeUserToVerifiedUsers(String identifier) {
        usersTable.removeUserToDataBase(identifier);
    }

    @Override
    public String getAdminIdentifier() {
        return usersTable.getAdminIdentifier();
    }

    @Override
    public ArrayList<String> getUserFromVerifiedUsers(String identifier) {
        return usersTable.getUserToDataBase(identifier);
    }

    @Override
    public void addUserToNoVerifiedUsers(UserBot user) {
        noVerUsersGroup.addUserBot(user);
    }

    @Override
    public UserBot searchUserInNoVerifiedUsers(String identifier) {
        return noVerUsersGroup.searchUserBot(identifier);
    }

    @Override
    public String getUsersGroupString() {
        return noVerUsersGroup.getUsersGroupString();
    }

    @Override
    public void removeUserToNoVerifiedUsers(String identifier) {
        noVerUsersGroup.removeUserBot(identifier);
    }
}
