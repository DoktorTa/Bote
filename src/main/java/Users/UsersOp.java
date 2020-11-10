package Users;

public class UsersOp implements IUsersOperation {
    private final VerUserBot verUserBot;
    private final NoVerUserBot noVerUserBot;


    public UsersOp(){
        verUserBot = new VerUserBot();
        noVerUserBot = new NoVerUserBot();
    }

    @Override
    public void addAdmin(UserBot newAdmin) {
        verUserBot.setAdmin(newAdmin);
    }

    @Override
    public UserBot getAdmins() {
        return verUserBot.getAdmin();
    }

    @Override
    public void addUserToNoVerGroup(UserBot user) {
        noVerUserBot.addUserBot(user);
    }

    @Override
    public boolean userInGroup(UserBot user) {
        return false;
    }
}
