package Command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Стандартный для всех команд класс.
 */
public abstract class AbsCommand extends BotCommand {

    protected void sendMsg(AbsSender sender, String msg, Chat chat){
        SendMessage message = new SendMessage();
        message.setChatId(chat.getId().toString());
        message.setText(msg);
        execute(sender, message);
    }

    public AbsCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    void execute(AbsSender sender, SendMessage message) {
        try {
            sender.execute(message);
        } catch (TelegramApiException ignored) {

        }
    }
}
