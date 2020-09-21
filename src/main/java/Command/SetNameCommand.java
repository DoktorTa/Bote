package Command;

import User.AnonymousService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

public final class SetNameCommand extends AnonymizerCommand {

    private final AnonymousService mAnonymouses;

    public SetNameCommand(AnonymousService anonymouses) {
        super("set_name", "set or change name that will be displayed with your messages\n");
        mAnonymouses = anonymouses;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String textMsg = "";

        log.info(user.getId() + getCommandIdentifier());

        if (!mAnonymouses.hasAnonymous(user)) {
            log.info("User {} is trying to execute '{}' without starting the bot!" + user.getId() + getCommandIdentifier());
            textMsg = getTextNoActiveStartCom();
            sendMSG(chat.getId().toString(), textMsg, user, absSender);
            return;
        }

        String displayedName = getName(strings);

        if (displayedName == null) {
            log.info("User {} is trying to set empty name." + user.getId());
            textMsg = getTextNullName();
            sendMSG(chat.getId().toString(), textMsg, user, absSender);
            return;
        }

        StringBuilder sb = new StringBuilder();

        String text_msg = checkUserNameNotUse(user, displayedName);

        sendMSG(chat.getId().toString(), sb.toString(), user, absSender);
    }

    private String checkUserNameNotUse(User user, String displayedName){
        String textMsg = "";

        if (mAnonymouses.setUserDisplayedName(user, displayedName)) {
            textMsg = checkRenameUser(user, displayedName);
        } else {
            log.info("User {} is trying to set taken name '{}'" + user.getId() + displayedName);
            textMsg = getTextNameAlreadyUse(displayedName);
        }

        return textMsg;
    }

    private String checkRenameUser(User user, String displayedName){
        String textMsg = "";
        if (mAnonymouses.getDisplayedName(user) == null) {
            log.info("User {} set a name '{}'" + user.getId() + displayedName);
            textMsg = getTextSetName(displayedName);
        } else {
            log.info("User {} has changed name to '{}'" + user.getId() + displayedName);
            textMsg = getTextChangeName(displayedName);
        }
        return textMsg;
    }

    private String getTextNoActiveStartCom(){
        StringBuilder textMsg = new StringBuilder();
        textMsg.append("Firstly you should start the bot! Execute '/start' command!");
        return textMsg.toString();
    }

    private String getTextNullName(){
        StringBuilder textMsg = new StringBuilder();
        textMsg.append("You should use non-empty name!");
        return textMsg.toString();
    }

    private String getTextSetName(String displayedName){
        StringBuilder textMsg = new StringBuilder();
        textMsg.append("Your displayed name: '")
                .append(displayedName)
                .append("'. Now you can send messages to bot!");
        return textMsg.toString();
    }

    private String getTextChangeName(String displayedName){
        StringBuilder textMsg = new StringBuilder();
        textMsg.append("Your new displayed name: '")
                .append(displayedName)
                .append("'.");
        return textMsg.toString();
    }

    private String getTextNameAlreadyUse(String displayedName){
        StringBuilder textMsg = new StringBuilder();
        textMsg.append("Name ")
                .append(displayedName)
                .append(" is already in use! Choose another name!");
        return textMsg.toString();
    }

    private String getName(String[] strings) {

        if (strings == null || strings.length == 0) {
            return null;
        }

        String name = String.join(" ", strings);
        return name.replaceAll(" ", "").isEmpty() ? null : name;
    }
}