package TelegramCommand;

import Commands.AbsCommand;
import Users.UserBot;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TelegramCommandAdapter extends BotCommand {
    private final Logger LOG;

    private final AbsCommand commandTelegram;

    public TelegramCommandAdapter(AbsCommand command, Logger log) {
        super(command.getIdentifierCommand(), command.getDescriptionCommand());
        commandTelegram = command;
        LOG = log;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {

        String msg = commandTelegram.getAnswer(new UserBot(user.getFirstName() + "_" + user.getUserName(), chat.getId().toString()), strings);

        SendMessage message = new SendMessage();
        message.setChatId(chat.getId().toString());
        message.setText(msg);

        try {
            absSender.execute(message);
        } catch (TelegramApiException te) {
            LOG.log(Level.WARNING, te.getMessage());
        }
    }
}
