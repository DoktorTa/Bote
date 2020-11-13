package Commands;

import Users.IUsersOperation;
import Users.NoVerUserBot;
import Users.UserBot;
import Users.VerUserBot;

public class DelCommand extends AbsCommand {

    public DelCommand(IUsersOperation userBot) {
        super("/del", "Remove user from bot.", userBot);
    }

    @Override
    public String getAnswer(UserBot user, String[] strings) {
        String textMSG = "";

        if (userIsAdmin(user.identifier)){
            textMSG = delInNoVerGroup(strings[0]);
        }

        return textMSG;
    }

    /**
     * @param identifier идентификатор пользователя.
     * @return String текст сообщения для отправки пользователю.
     */
    private String delInNoVerGroup(String identifier){
        UserBot user = usersBot.searchUserInNoVerGroup(identifier);

        if (user != null){

            usersBot.removeUserToNoVerGroup(user.getUser());
            return "User " + identifier + " deleted!";
        }
        return "User " + identifier + " not found in not verification list!";
    }
}
