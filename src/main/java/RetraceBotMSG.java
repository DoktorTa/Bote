import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;


public class RetraceBotMSG extends TelegramLongPollingCommandBot {

    private static final String BOT_TOKEN = "";
    private static final String BOT_NAME = "RetraceBotMSG";

    public RetraceBotMSG(String nameAdmin) {
        register(new StartCommand());
//        register(new SetNameCommand());
//        register(new SendMSGToUser());
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public void processNonCommandUpdate(Update update) {

    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

//    @Override
//    public void onUpdateReceived(Update update) {
//        // We check if the update has a message and the message has text
//        if (update.hasMessage() && update.getMessage().hasText()) {
//            SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
//                    .setChatId(update.getMessage().getChatId())
//                    .setText(update.getMessage().getText());
//            try {
//                execute(message); // Call method to send the message
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
