import Command.PendingVerCommand;
import Command.StartCommand;
import Command.VerCommand;
import Users.NoVerUserBot;
import Users.VerUserBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;


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
