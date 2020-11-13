package Users;

import org.telegram.telegrambots.meta.api.objects.User;

import java.util.ArrayList;

public interface IUsersOperation {

    public void addAdmin(UserBot newAdmin);

    public String getAdminIdentifier();

    public void addUserToNoVerGroup(UserBot user);

    public void addUserToVerGroup(UserBot user);

    public void removeUserToNoVerGroup(User user);

    public void removeUserToVerGroup(String identifier);

    public ArrayList<String> getUserFromVerUser(String user);

    public UserBot searchUserInNoVerGroup(String identifier);

    public String getUsersGroupString();
}
