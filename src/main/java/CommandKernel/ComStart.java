package CommandKernel;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.util.logging.Level;
import java.util.logging.Logger;

abstract public class ComStart {

    protected static final Logger Logs = Logger.getLogger(ComStart.class.getName());
    private static final String startMsg = "Добро пожаловать, ";
    private static final String startCommand = "/start";

    public ComStart(){
        Logs.info("ComStart");
    }

    public static String getStartMsg(){
        return startMsg;
    }

    public static String getStartCommand(){
        return startCommand;
    }

    public abstract void execute();

    public abstract String getTextMsg();
}

public class ComTelegramStart extends ComStart{
    public ComTelegramStart() {
        super();
    }


    @Override
    public void execute(){
        Logs.info(user.getId(), getCommandIdentifier());

        StringBuilder sb = new StringBuilder();

        SendMessage message = new SendMessage();
        message.setChatId(chat.getId().toString());

        if (mAnonymouses.addAnonymous(new Anonymous(user, chat))) {
            Logs.info("User {} is trying to execute '{}' the first time. Added to users' list.", user.getId(), getCommandIdentifier());
            sb.append("Hi, ")
                    .append(user.getUserName())
                    .append("! You've been added to bot users' list!\n")
                    .append("Please execute command:\n'/set_name <displayed_name>'\nwhere &lt;displayed_name&gt; is the name you want to use to hide your real name.");
        } else {
            Logs.log(Level.INFO(Level.STRANGE.getValue()), "User {} has already executed '{}'. Is he trying to do it one more time?", user.getId(), getCommandIdentifier());
            sb.append("You've already started bot! You can send messages if you set your name (/set_name).");
        }

        message.setText(sb.toString());
        execute(absSender, message, user);
    }

    @Override
    private static String getTextMsg(){
        return textMsg;
    }
}