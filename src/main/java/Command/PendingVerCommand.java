package Command;

import Users.NoVerUserBot;
import Users.UserBot;
import Users.VerUserBot;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

public class PendingVerCommand extends AbsCommand {

    private final VerUserBot mVerUsersGroup;
    private final NoVerUserBot mNoVerUsersGroup;

    public PendingVerCommand(VerUserBot verUsersGroup, NoVerUserBot noVerUsersGroup) {
        super("/pending_ver", "Displays users awaiting verification.");
        mVerUsersGroup = verUsersGroup;
        mNoVerUsersGroup = noVerUsersGroup;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String textMSG = "";

        if (userIsAdmin(user, chat)){
            textMSG = getBeadrollNoVerUser();
            sendMsg(absSender, textMSG, chat, user);
        }

    }

    //TODO: Убрать проверку в одно место - кододублирование говно.
    private boolean userIsAdmin(User user, Chat chat){
        return mVerUsersGroup.getAdmin().equals(new UserBot(user, chat));
    }

    private String getBeadrollNoVerUser(){
        return mNoVerUsersGroup.getUsersGroupString();
    }
}
