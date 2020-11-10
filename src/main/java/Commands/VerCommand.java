package Commands;

import Users.IUsersOperation;
import Users.UserBot;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;

public class VerCommand extends AbsCommand{

    public VerCommand(IUsersOperation userBot){
        super("/ver", "Verification user", userBot);
    }

    @Override
    public String getAnswer(UserBot user, String[] strings) {
        String textMSGToAdmin = "";

        if (userIsAdmin(user.identifier)) {
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
        UserBot user = usersBot.searchUserInNoVerGroup(identifier);

        if (user != null){
            usersBot.removeUserToNoVerGroup(user.getUser());
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
        usersBot.addUserToVerGroup(user);
        return "User " + user.identifier + " verification!";
    }
}
