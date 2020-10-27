package Command;

import Users.NoVerUserBot;
import Users.UserBot;
import Users.VerUserBot;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

public class StartCommand extends AbsCommand{
    private final VerUserBot mVerUsersGroup;
    private final NoVerUserBot mNoVerUsersGroup;

    public StartCommand(VerUserBot verUsersGroup, NoVerUserBot noVerUsersGroup) {
        super("/start", "Starting retrace msg to admin.");
        mVerUsersGroup = verUsersGroup;
        mNoVerUsersGroup = noVerUsersGroup;

    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String textMSG = "";

        if (adminExistence()){
            textMSG = createAdmin(user, chat);
        }
        else if(mVerUsersGroup.userInGroup(new UserBot(user, chat))){
            textMSG = "You are verified";
        }
        else {
            textMSG = addNoVerUser(user, chat);
        }

        sendMsg(absSender, textMSG, chat, user);
    }

    private String addNoVerUser(User user, Chat chat){
        UserBot userBot = new UserBot(user, chat);
        mNoVerUsersGroup.addUserBot(userBot);
        return "Welcome, await administrator verification";
    }

    private boolean adminExistence(){
        return mVerUsersGroup.getAdmin() == null;
    }

    private String createAdmin(User user, Chat chat){
        UserBot userBot = new UserBot(user, chat);
        mVerUsersGroup.setAdmin(userBot);
        return "Hello my admin!";
    }
}
