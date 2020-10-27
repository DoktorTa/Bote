package Command;

import Users.NoVerUserBot;
import Users.UserBot;
import Users.VerUserBot;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

public class DelCommand extends AbsCommand {
    private final VerUserBot mVerUsersGroup;
    private final NoVerUserBot mNoVerUsersGroup;

    public DelCommand(VerUserBot verUsersGroup, NoVerUserBot noVerUsersGroup) {
        super("/del", "Remove user from bot.");
        mVerUsersGroup = verUsersGroup;
        mNoVerUsersGroup = noVerUsersGroup;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String textMSG = "";

        if (userIsAdmin(user, chat)){
           textMSG = delInNoVerGroup(strings[0]);
            sendMsg(absSender, textMSG, chat, user);
        }
    }

    //TODO: Убрать проверку в одно место - кододублирование говно.
    private boolean userIsAdmin(User user, Chat chat){
        return mVerUsersGroup.getAdmin().equals(new UserBot(user, chat));
    }

    private String delInNoVerGroup(String identifier){
        UserBot userBot = mNoVerUsersGroup.searchUserBot(identifier);

        if (userBot != null){
            mNoVerUsersGroup.removeUserBot(userBot.getUser());
            return "User " + identifier + " deleted!";
        }
        return "User " + identifier + " not found in not verification list!";
    }

}
