package Command;

import Users.NoVerUserBot;
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
        String textMSG = "";

        textMSG = strings[0];

        sendMsg(absSender, textMSG, chat, user);
    }
}
