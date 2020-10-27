package Command;

import Users.NoVerUserBot;
import Users.UserBot;
import Users.VerUserBot;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

public class VerCommand extends AbsCommand {
    private final VerUserBot mVerUsersGroup;
    private final NoVerUserBot mNoVerUsersGroup;

    public VerCommand(VerUserBot verUsersGroup, NoVerUserBot noVerUsersGroup) {
        super("/ver", "Verifies the user by the identifier, if he is in the list of unverified users.");
        mVerUsersGroup = verUsersGroup;
        mNoVerUsersGroup = noVerUsersGroup;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String textMSGToAdmin = "";

        if (userIsAdmin(user, chat)){
            UserBot userBot = deleteNoVerUser(strings[0]);

            if (userBot == null){
                textMSGToAdmin = "User not found!";
            }
            else {
                textMSGToAdmin = verificationUser(userBot);
                if (textMSGToAdmin == null){
                    return;
                }
                sendMsg(absSender, "You verification!", userBot.getChat(), userBot.getUser());
            }

            sendMsg(absSender, textMSGToAdmin, chat, user);
        }
    }

    //TODO: Убрать проверку в одно место - кододублирование говно.
    private boolean userIsAdmin(User user, Chat chat){
        return mVerUsersGroup.getAdmin().equals(new UserBot(user, chat));
    }

    //TODO: Кододублирование говно.
    private UserBot deleteNoVerUser(String identifier){
        UserBot userBot = mNoVerUsersGroup.searchUserBot(identifier);

        if (userBot != null){
            mNoVerUsersGroup.removeUserBot(userBot.getUser());
            return userBot;
        }

        return null;
    }

    private String verificationUser(UserBot userBot){
        mVerUsersGroup.addUserBot(userBot);
        return "User " + userBot.identifier + " verification!";
    }
}
