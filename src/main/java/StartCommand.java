import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.util.logging.Logger;

public class StartCommand extends AbsCommand{
    private final VerUserBot mVerUsersGroup;
    private final Logger LOG = Logger.getLogger(StartCommand.class.getName());
//    private final NoVerUserBot noVerUsersGroup;

    public StartCommand(VerUserBot verUsersGroup) {
        super("/start", "Starting retrace msg to admin.");
        mVerUsersGroup = verUsersGroup;
//        noVerUsersGroup = noVerUsersGroup;

    }


    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String textMSG = "Error";

        if (adminExistence()){
            textMSG = createAdmin(user);
            LOG.info(mVerUsersGroup.getAdmin().identifier);
        }
        else {
            textMSG = addNoVerUser(user);
        }

        sendMsg(absSender, textMSG, chat, user);
    }

    private String addNoVerUser(User user){

        return "Welcome, await administrator verification";
    }

    private boolean adminExistence(){
        return mVerUsersGroup.getAdmin() == null;
    }

    private String createAdmin(User user){
        UserBot userBot = new UserBot(user);
        mVerUsersGroup.setAdmin(userBot);
        String textMSG = "Hello my admin!";
        return textMSG;
    }
}
