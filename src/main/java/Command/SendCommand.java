package Command;

import Users.NoVerUserBot;
import Users.UserBot;
import Users.VerUserBot;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.util.Arrays;

public class SendCommand extends AbsCommand{
    private final VerUserBot mVerUsersGroup;
    private final NoVerUserBot mNoVerUsersGroup;

    public SendCommand(VerUserBot verUsersGroup, NoVerUserBot noVerUsersGroup) {
        super("/del", "Remove user from bot.");
        mVerUsersGroup = verUsersGroup;
        mNoVerUsersGroup = noVerUsersGroup;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String textMSG = "";

        if (userIsAdmin(user, chat)){
            textMSG = Arrays.toString(strings);
            sendMsg(absSender, textMSG, chat, user);
        }
    }

    //TODO: Кододублирование говно.
    private UserBot getUserBotByIdentifier(String identifier){
        UserBot userBot = mVerUsersGroup.searchUserBot(identifier);
        if (userBot != null){
            mNoVerUsersGroup.removeUserBot(userBot.getUser());
            return userBot;
        }

        return null;
    }

    //TODO: Убрать проверку в одно место - кододублирование говно.
    private boolean userIsAdmin(User user, Chat chat){
        return mVerUsersGroup.getAdmin().equals(new UserBot(user, chat));
    }
}
