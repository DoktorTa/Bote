package Command;

import Users.NoVerUserBot;
import Users.UserBot;
import Users.VerUserBot;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

public class StopCommand extends AbsCommand{
    private final VerUserBot mVerUsersGroup;
    private final NoVerUserBot mNoVerUsersGroup;

    public StopCommand(VerUserBot verUsersGroup, NoVerUserBot noVerUsersGroup) {
        super("/stop", "Stops communication with the administrator, ATTENTION! You are losing your verification.");
        mVerUsersGroup = verUsersGroup;
        mNoVerUsersGroup = noVerUsersGroup;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String textMSG = "";

        if (!userIsAdmin(user, chat)){
            textMSG = delInVerGroup(user);
            sendMsg(absSender, textMSG, chat, user);
        }
    }

    //TODO: Убрать проверку в одно место - кододублирование говно.
    private boolean userIsAdmin(User user, Chat chat){
        return mVerUsersGroup.getAdmin().equals(new UserBot(user, chat));
    }

    private String delInVerGroup(User user){
        mVerUsersGroup.removeUserBot(user);
        return "STOP";
    }
}
