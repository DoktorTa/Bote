package Users;

public interface IUsersOperation {

    public void addAdmin(UserBot newAdmin);

    public UserBot getAdmins();

    public void addUserToNoVerGroup(UserBot user);

    public boolean userInGroup(UserBot user);

}
