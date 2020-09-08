package Command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

import Command.AbsCommand;

import java.util.logging.LogManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ComStart extends AbsCommand {

    private static final Logger Logs = LogManager.getLogManager().getLogger(ComStart.class.getName());
    private final AnonService mAnon;

    public ComStart(AnonymosService anon){
        super("start", "start using bot\n");
        mAnon = anon;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings){
        /**
         * Команда начала общения.
         * @param absSender - отправляет ответ пользователю
         * @param user - пользователь, который выполнил команду
         * @param chat - чат бота и пользователя
         * @param strings - аргументы, переданные с командой
         */
        String logs_str = "Start coamand initilaize: " + user.getUserName();
        Logs.info(logs_str);

        StringBuilder sb = new StringBuilder();

    }

    protected void sendMsg(Chat chat){
        SendMessage messange = new SendMessage();
        messange.setChatId()

    }
}


public final class StartCommand extends AnonymizerCommand {

    /**
     * реализованный метод класса BotCommand, в котором обрабатывается команда, введенная пользователем
     * @param absSender - отправляет ответ пользователю
     * @param user - пользователь, который выполнил команду
     * @param chat - чат бота и пользователя
     * @param strings - аргументы, переданные с командой
     */
    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {

        log.info(LogTemplate.COMMAND_PROCESSING.getTemplate(), user.getId(), getCommandIdentifier());

        StringBuilder sb = new StringBuilder();

        SendMessage message = new SendMessage();
        message.setChatId(chat.getId().toString());

        if (mAnonymouses.addAnonymous(new Anonymous(user, chat))) {
            log.info("User {} is trying to execute '{}' the first time. Added to users' list.", user.getId(), getCommandIdentifier());
            sb.append("Hi, ").append(user.getUserName()).append("! You've been added to bot users' list!\n")
                    .append("Please execute command:\n'/set_name <displayed_name>'\nwhere &lt;displayed_name&gt; is the name you want to use to hide your real name.");
        } else {
            log.log(Level.getLevel(LogLevel.STRANGE.getValue()), "User {} has already executed '{}'. Is he trying to do it one more time?", user.getId(), getCommandIdentifier());
            sb.append("You've already started bot! You can send messages if you set your name (/set_name).");
        }

        message.setText(sb.toString());
        execute(absSender, message, user);
    }
}