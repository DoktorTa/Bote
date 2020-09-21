package Command;

import User.AnonymousService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

public final class MyNameCommand extends AnonymizerCommand {

    private final AnonymousService mAnonymouses;

    public MyNameCommand(AnonymousService anonymouses) {
        super("my_name", "show your current name that will be displayed with your messages\n");
        mAnonymouses = anonymouses;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String textMsg = "";
        String userId = user.getId().toString();

        log.info(user.getId() + getCommandIdentifier());

        if (!mAnonymouses.hasAnonymous(user)) {
            log.info("User " + userId
                    + " is trying to execute " +  getCommandIdentifier() + " without starting the bot.");
            textMsg = getTextNotRegUser();
        } else if(mAnonymouses.getDisplayedName(user) == null) {
            log.info("User " + userId
                    + " is trying to execute " +  getCommandIdentifier() + " without having a name.");
            textMsg = getTextNotNameUser();
        } else {
            String nameUser = mAnonymouses.getDisplayedName(user);
            textMsg = getTextNameUser(nameUser);
            log.info("User " + userId +
                    "is executing " +  getCommandIdentifier()
                    + ". Name is:" + nameUser);
        }

        sendMSG(chat.getId().toString(), textMsg, user, absSender);
    }

    private static String getTextNotRegUser(){
        StringBuilder textMsg = new StringBuilder();
        textMsg.append("You are not in bot users' list! Send /start command!");
        return textMsg.toString();
    }

    private static String getTextNotNameUser(){
        StringBuilder textMsg = new StringBuilder();
        textMsg.append("Currently you don't have a name.\nSet it using command:\n'/set_name &lt;displayed_name&gt;'");
        return textMsg.toString();
    }

    private static String getTextNameUser(String nameUser){
        StringBuilder textMsg = new StringBuilder();
        textMsg.append("Your current name: ")
                .append(nameUser);
        return textMsg.toString();
    }

}
