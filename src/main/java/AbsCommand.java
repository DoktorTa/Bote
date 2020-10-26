import org.telegram.telegrambots.extensions.bots.commandbot.commands.ICommandRegistry;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.logging.LogManager;
import java.util.logging.Logger;


public abstract class AbsCommand extends BotCommand {

    public final Logger log = LogManager.getLogManager().getLogger(getClass().getName());

    protected void sendMsg(AbsSender sender, String msg, Chat chat, User user){
        SendMessage message = new SendMessage();
        message.setChatId(chat.getId().toString());
        message.setText(msg);
        execute(sender, message, user);
    }

    public AbsCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    void execute(AbsSender sender, SendMessage message, User user) {
        try {
            sender.execute(message);
        } catch (TelegramApiException ignored) {

        }
    }
}
