package Commands;

import Users.IUsersOperation;
import Users.UserBot;

public class VerCommand extends AbstractCommand {

    public VerCommand(IUsersOperation userBot){
        super("/ver", "Verification user", userBot);
    }

    @Override
    public String getAnswer(UserBot user, String[] strings) {
        String textMSGToAdmin = "";

        if (userIsAdmin(user.getIdentifier())) {
            UserBot userNoVer = deleteNoVerUser(strings[0]);

            if (userNoVer == null) {
                textMSGToAdmin = "User not found!";
            } else {
                textMSGToAdmin = verificationUser(userNoVer);
            }

        }
        return textMSGToAdmin;
    }

    /**
     * Удаление из списка не верифицированных пользователей.
     * @param identifier идентификатор пользователя.
     * @return UserBot or null. Удаленный пользователь.
     */
    private UserBot deleteNoVerUser(String identifier){
        UserBot user = usersBot.searchUserInNoVerifiedUsers(identifier);

        if (user != null){
            usersBot.removeUserToNoVerifiedUsers(user.getIdentifier());
            return user;
        }

        return null;
    }

    /**
     * Добавление пользователя в список верифицированных.
     * @param user пользователь.
     * @return String текст успешной верификации.
     */
    private String verificationUser(UserBot user){
        usersBot.addUserToVerifiedUsers(user);
        return "User " + user.getIdentifier() + " verification!";
    }
}
