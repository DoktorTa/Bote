package Command;

import User.AnonymousService;
import User.Anonymous;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

public final class StartCommand extends AnonymizerCommand {
    /*Класс начинает работу с ботом, при этом отправляя сообщение с последующими действиями.
    * В случае уже начатой раньше работой с ботом данным пользователем отправляет ощибку.*/

    private final AnonymousService mAnonymouses;

    public StartCommand(AnonymousService anonymouses) {
        super("start", "start using bot\n");
        mAnonymouses = anonymouses;
    }

    /**
     * @param absSender - отправляет ответ пользователю
     * @param user - пользователь, который выполнил команду
     * @param chat - чат бота и пользователя
     * @param strings - аргументы, переданные с командой
     */
    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String textMsg = "";
        String userId = user.getId().toString();

        log.info(user.getId() + getCommandIdentifier());

        if (mAnonymouses.addAnonymous(new Anonymous(user, chat))) {
            log.info("User " + userId
                    + " is trying to execute " + getCommandIdentifier()
                    + " the first time. Added to users' list." );
            textMsg = setGoodAnswer(user.getUserName());
        }
        else {
            log.info("User " + userId
                    + " has already executed " + getCommandIdentifier()
                    + ". Is he trying to do it one more time?");
            textMsg = setBadAnswer();
        }

        sendMSG(chat.getId().toString(), textMsg, user, absSender);
    }

    /**
     * @param userName - Имя пользователя.
     */
    private static String setGoodAnswer(String userName){
        StringBuilder textMsg = new StringBuilder();
        textMsg.append("Hi, ")
                .append(userName)
                .append("! You've been added to bot users' list!\n")
                .append("Please execute command:\n'/set_name <displayed_name>'\nwhere &lt;displayed_name&gt; is the name you want to use to hide your real name.");
        return textMsg.toString();
    }

    private static String setBadAnswer(){
        StringBuilder textMsg = new StringBuilder();
        textMsg.append("You've already started bot! You can send messages if you set your name (/set_name).");
        return textMsg.toString();
    }
}
