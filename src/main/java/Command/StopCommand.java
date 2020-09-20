package Command;

import User.AnonymousService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

public final class StopCommand extends AnonymizerCommand {

    private final AnonymousService mAnonymouses;

    public StopCommand(AnonymousService anonymouses) {
        super("stop", "remove yourself from bot users' list\n");
        mAnonymouses = anonymouses;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String userId = user.getId().toString();
        String textMsg = "";

        log.info(user.getId() + getCommandIdentifier());

        if (mAnonymouses.removeAnonymous(user)) {
            log.info("User " + userId +
                    " has been removed from users list!");
            textMsg = setGoodAnswer(user.getUserName());
        }
        else {
            log.info("User " + userId +
                    " is trying to execute " + getCommandIdentifier() + " without having executed 'start' before!");
            textMsg = setBadAnswer();
        }

        sendMSG(chat.getId().toString(), textMsg, user, absSender);
    }

    /**
     * @param userName - Имя пользователя.
     */
    private String setGoodAnswer(String userName){
        StringBuilder textMsg = new StringBuilder();
        textMsg.append(userName)
                .append(" you've been removed from bot's users list! Bye!");
        return textMsg.toString();
    }

    private String setBadAnswer(){
        StringBuilder textMsg = new StringBuilder();
        textMsg.append("You were not in bot users' list. Bye!");
        return textMsg.toString();
    }
}