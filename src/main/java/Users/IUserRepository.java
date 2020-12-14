package Users;

import java.util.ArrayList;

public interface IUserRepository {

    void createUser(String identifier, String chatId);

    ArrayList<String> getAllCompleteTaskUser(String identifier);


}
