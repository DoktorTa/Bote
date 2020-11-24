package Commands.User;

import Commands.AbstractCommand;
import Users.IUsersRepository;
import Users.UserBot;

public class DelCommand extends AbstractCommand {

    public DelCommand(IUsersRepository userBot) {
        super("/del", "Remove user from bot.", userBot);
    }

    @Override
    public String getAnswer(UserBot user, String[] strings) {
        String textMSG = "";

        if (userIsAdmin(user.getIdentifier())){
            textMSG = delInNoVerGroup(strings[0]);
        }

        return textMSG;
    }

    /**
     * @param identifier идентификатор пользователя.
     * @return String текст сообщения для отправки пользователю.
     */
    private String delInNoVerGroup(String identifier){
        UserBot user = usersBot.searchUserInNoVerifiedUsers(identifier);

        if (user != null){

            usersBot.removeUserToNoVerifiedUsers(identifier);
            return "User " + identifier + " deleted!";
        }
        return "User " + identifier + " not found in not verification list!";
    }
}
