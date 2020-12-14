package Users;

import DataBase.IDataBaseUser;

import java.util.ArrayList;

public class UserRepository implements IUserRepository{
    public final IDataBaseUser iDataBaseUser;

    public UserRepository(IDataBaseUser iDataBaseUser1){
        iDataBaseUser = iDataBaseUser1;
    }

    @Override
    public void createUser(String identifier, String chatId) {
        iDataBaseUser.addUserToDataBase(identifier, chatId, 0, 0);
    }

    @Override
    public ArrayList<String> getAllCompleteTaskUser(String identifier) {
        int packedAllCompleteTask = iDataBaseUser.getAllCompleteTaskUser(identifier);
        return unpackedAllTaskCompleteTask(packedAllCompleteTask);
    }

    private ArrayList<String> unpackedAllTaskCompleteTask(int packedAllCompleteTask){
        return new ArrayList<String>();
    }
}
