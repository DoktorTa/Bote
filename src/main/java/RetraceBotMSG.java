import Command.*;
import Users.NoVerUserBot;
import Users.VerUserBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class RetraceBotMSG extends TelegramLongPollingCommandBot {

    private static final String BOT_TOKEN = "1385670919:AAGakkLJt0rINFK3_qza2SfHIrmOuj2o4iM";
    private static final String BOT_NAME = "RetraceBotMSG";

    private final VerUserBot verUsersGroup;
    private final NoVerUserBot noVerUsersGroup;

    public RetraceBotMSG(String nameAdmin) {
        verUsersGroup = new VerUserBot();
        noVerUsersGroup = new NoVerUserBot();

        register(new StartCommand(verUsersGroup, noVerUsersGroup));
        register(new PendingVerCommand(verUsersGroup, noVerUsersGroup));
        register(new VerCommand(verUsersGroup, noVerUsersGroup));
        register(new DelCommand(verUsersGroup, noVerUsersGroup));
        register(new StopCommand(verUsersGroup, noVerUsersGroup));
        register(new SendCommand(verUsersGroup, noVerUsersGroup));
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void processNonCommandUpdate(Update update) {

        if (!update.hasMessage()) {
            throw new IllegalStateException("Update doesn't have a body!");
        }

        Message msg = update.getMessage();
        User user = msg.getFrom();

//        if (!canSendMessage(user, msg)) {
//            return;
//        }

        String clearMessage = msg.getText();
        SendMessage answer = new SendMessage();

        answer.setText(clearMessage);
//        answer.setChatId(msg.getChatId());
//        replyToUser(answer, user, clearMessage);

        answer.setChatId(verUsersGroup.getAdmin().getChat().getId());
        try {
            execute(answer);
        } catch (TelegramApiException ignored) {
        }
    }


}
