import Commands.PendingVerCommand;
import Commands.StartCommand;
import Commands.VerCommand;
import DataBase.DataBaseMSSQL;
import DataBase.MSSQLUserTable;
import TelegramCommand.TelegramCommandAdapter;
import Users.NoVerUserBot;
import Users.UserBot;
import Users.UsersOperation;
import Users.VerUserBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.logging.Logger;


public class RetraceBotMSG extends TelegramLongPollingCommandBot {
    private final Logger LOG;

    private static final String BOT_TOKEN = System.getenv("TOKEN");
    private static final String BOT_NAME = System.getenv("NAME_BOT");

    private final VerUserBot verUsersGroup;
    private final NoVerUserBot noVerUsersGroup;

    public RetraceBotMSG(Logger log) {
        LOG = log;
        verUsersGroup = new VerUserBot();
        noVerUsersGroup = new NoVerUserBot();

        DataBaseMSSQL dataBaseMSSQL = new DataBaseMSSQL(LOG);
        UsersOperation userOp = new UsersOperation(new MSSQLUserTable(LOG, dataBaseMSSQL.connectDataBase()));

        register(new TelegramCommandAdapter(new StartCommand(userOp), LOG));
        register(new TelegramCommandAdapter(new PendingVerCommand(userOp), LOG));
        register(new TelegramCommandAdapter(new VerCommand(userOp), LOG));
//        register(new DelCommand(verUsersGroup, noVerUsersGroup));
//        register(new StopCommand(verUsersGroup, noVerUsersGroup));
//        register(new SendCommand(verUsersGroup, noVerUsersGroup));
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
        Chat chat = msg.getChat();

        if (userIsNoVer(user, chat)){
            return;
        }

        SendMessage answer = new SendMessage();
        answer.setText(msg.getText());
        answer.setChatId(verUsersGroup.getAdmin().getChat().getId());

        try {
            execute(answer);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param user пользователь телеграмма.
     * @param chat чат пользователя.
     * @return верифицирован ли пользователь.
     */
    private boolean userIsNoVer(User user, Chat chat){
        return !(verUsersGroup.userInGroup(new UserBot(user, chat)));
    }


}
