package Command;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.logging.Logger;

abstract class AnonymizerCommand extends BotCommand {

    final Logger log = Logger.getLogger(AnonymizerCommand.class.getName());

    AnonymizerCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    protected void sendMSG(String chatId, String msg, User user, AbsSender absSender){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(msg);
        execute(absSender, message, user);
    }

    void execute(AbsSender sender, SendMessage message, User user) {
        try {
            sender.execute(message);
            log.info(user.getId() + getCommandIdentifier());
        } catch (TelegramApiException e) {
            log.info(user.getId() + getCommandIdentifier() + e);
        }
    }
}
