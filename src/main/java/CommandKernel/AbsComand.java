package CommandKernel;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

abstract class AbsCommand extends BotCommand {

    private static final Logger LOG = LogManager.getLogManager().getLogger(AbsCommand.class.getName());

    AbsCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    void execute(AbsSender sender, SendMessage message, User user) {
        try {
            sender.execute(message);
        } catch (TelegramApiException e) {
            LOG.log(Level.SEVERE, "Command error", e);
        }
    }

    protected SendMessage getMsg(Chat chat, String textMsg){
        /*
         * Собирает и возврашет сообщение.
         */
        StringBuilder sb = new StringBuilder();
        sb.append(textMsg);

        SendMessage messange = new SendMessage();
        String chatId = chat.getId().toString();
        messange.setChatId(chatId);
        messange.setText(sb.toString());
        return messange;
    }

}