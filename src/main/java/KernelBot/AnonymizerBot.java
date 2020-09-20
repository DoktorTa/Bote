package KernelBot;

import Command.*;
import User.*;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.logging.Logger;
import java.util.stream.Stream;

public final class AnonymizerBot extends TelegramLongPollingCommandBot {

    private static final Logger LOG = Logger.getLogger(AnonymizerBot.class.getName());
    private static final String BOT_NAME = "RetraceMSGbot";
    private static final String BOT_TOKEN = "1385670919:AAGakkLJt0rINFK3_qza2SfHIrmOuj2o4iM";

    private final AnonymousService mAnonymouses;

    public AnonymizerBot(DefaultBotOptions botOptions) {

        super(botOptions, Boolean.parseBoolean(BOT_NAME));
        mAnonymouses = new AnonymousService();

        registerCommand();
    }

    private void setCommands(){
        return;
    }

    public void registerCommand(){
        register(new StartCommand( mAnonymouses));
        register(new SetNameCommand(mAnonymouses));
        register(new StopCommand(mAnonymouses));
        register(new MyNameCommand(mAnonymouses));
        HelpCommand helpCommand = new HelpCommand(this);
        register(helpCommand);

        registerDefaultAction(((absSender, message) -> {

            LOG.info("User {} is trying to execute unknown command '{}'." + message.getFrom().getId() + message.getText());

            SendMessage text = new SendMessage();
            text.setChatId(message.getChatId());
            text.setText(message.getText() + " command not found!");

            try {
                absSender.execute(text);
            } catch (TelegramApiException e) {
                LOG.info("Error while replying unknown command to user {}." + message.getFrom() + e);
            }

            helpCommand.execute(absSender, message.getFrom(), message.getChat(), new String[] {});
        }));
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }


//    ---------------------------------------------------------------------------------------------------------------

    // обработка сообщения не начинающегося с '/'
    @Override
    public void processNonCommandUpdate(Update update) {

        LOG.info("Processing non-command update...");

        if (!update.hasMessage()) {
            LOG.info("Update doesn't have a body!");
            throw new IllegalStateException("Update doesn't have a body!");
        }

        Message msg = update.getMessage();
        User user = msg.getFrom();

        LOG.info(user.getId().toString());

        if (!canSendMessage(user, msg)) {
            return;
        }

        String clearMessage = msg.getText();
        String messageForUsers = String.format("%s:\n%s", mAnonymouses.getDisplayedName(user), msg.getText());

        SendMessage answer = new SendMessage();

        // отправка ответа отправителю о том, что его сообщение получено
        answer.setText(clearMessage);
        answer.setChatId(msg.getChatId());
        replyToUser(answer, user, clearMessage);

        // отправка сообщения всем остальным пользователям бота
        answer.setText(messageForUsers);
        Stream<Anonymous> anonymouses = mAnonymouses.anonymouses();
        anonymouses.filter(a -> !a.getUser().equals(user))
                .forEach(a -> {
                    answer.setChatId(a.getChat().getId());
                    sendMessageToUser(answer, a.getUser(), user);
                });
    }

    // несколько проверок, чтобы можно было отправлять сообщения другим пользователям
    private boolean canSendMessage(User user, Message msg) {

        SendMessage answer = new SendMessage();
        answer.setChatId(msg.getChatId());

        if (!msg.hasText() || msg.getText().trim().length() == 0) {
            LOG.info("User {} is trying to send empty message!" + user.getId());
            answer.setText("You shouldn't send empty messages!");
            replyToUser(answer, user, msg.getText());
            return false;
        }

        if(!mAnonymouses.hasAnonymous(user)) {
            LOG.info("User {} is trying to send message without starting the bot!" + user.getId());
            answer.setText("Firstly you should start bot! Use /start command!");
            replyToUser(answer, user, msg.getText());
            return false;
        }

        if (mAnonymouses.getDisplayedName(user) == null) {
            LOG.info("User {} is trying to send message without setting a name!" + user.getId());
            answer.setText("You must set a name before sending messages.\nUse '/set_name <displayed_name>' command.");
            replyToUser(answer, user, msg.getText());
            return false;
        }

        return true;
    }

    private void sendMessageToUser(SendMessage message, User receiver, User sender) {
        try {
            execute(message);
            LOG.info(receiver.getId().toString() + sender.getId());
        } catch (TelegramApiException e) {
            LOG.info(receiver.getId().toString() + sender.getId() + e);
        }
    }

    private void replyToUser(SendMessage message, User user, String messageText) {
        try {
            execute(message);
            LOG.info(user.getId() + messageText);
        } catch (TelegramApiException e) {
            LOG.info(user.getId().toString() + e);
        }
    }

    //---------------------------------------------------------------------------------------------------------------
}
