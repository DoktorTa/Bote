package Users;

import DataBase.IDataBaseUser;

public class UserRepository implements IUserRepository{
    public final IDataBaseUser iDataBaseUser;

    public UserRepository(IDataBaseUser iDataBaseUser1){
        iDataBaseUser = iDataBaseUser1;
    }

    @Override
    public void createUser(String identifier, String chatId) {
        iDataBaseUser.addUserToDataBase(identifier, chatId, 0, 0);
    }
}
