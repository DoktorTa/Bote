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

        log.info(user.getId() + getCommandIdentifier());

        if (!mAnonymouses.hasAnonymous(user)) {
            log.info("User {} is trying to execute '{}' without starting the bot!" + user.getId() + getCommandIdentifier());
            String textMsg = "Firstly you should start the bot! Execute '/start' command!";
            sendMSG(chat.getId().toString(), textMsg, user, absSender);
            return;
        }

        String displayedName = getName(strings);

        if (displayedName == null) {
            log.info("User {} is trying to set empty name." + user.getId());
            String textMsg = "You should use non-empty name!";
            sendMSG(chat.getId().toString(), textMsg, user, absSender);
            return;
        }

        StringBuilder sb = new StringBuilder();

        if (mAnonymouses.setUserDisplayedName(user, displayedName)) {

            if (mAnonymouses.getDisplayedName(user) == null) {
                log.info("User {} set a name '{}'" + user.getId() + displayedName);
                sb.append("Your displayed name: '").append(displayedName)
                        .append("'. Now you can send messages to bot!");
            } else {
                log.info("User {} has changed name to '{}'" + user.getId() + displayedName);
                sb.append("Your new displayed name: '").append(displayedName).append("'.");
            }
        } else {
            log.info("User {} is trying to set taken name '{}'" + user.getId() + displayedName);
            sb.append("Name ").append(displayedName).append(" is already in use! Choose another name!");
        }

        sendMSG(chat.getId().toString(), sb.toString(), user, absSender);
    }

    private String getName(String[] strings) {

        if (strings == null || strings.length == 0) {
            return null;
        }

        String name = String.join(" ", strings);
        return name.replaceAll(" ", "").isEmpty() ? null : name;
    }
}