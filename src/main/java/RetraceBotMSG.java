import Commands.*;
import DataBase.DataBaseMSSQL;
import DataBase.MSSQLTaskTable;
import DataBase.MSSQLUserTable;
import Tasks.TaskOperation;
import TelegramCommand.TelegramCommandAdapter;
import Users.NoVerUserBot;
import Users.UserBot;
import Users.UsersOperation;
import Users.VerUserBot;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.User;

import java.sql.Statement;
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
        Statement stmt = dataBaseMSSQL.connectDataBase();
        UsersOperation userOp = new UsersOperation(new MSSQLUserTable(LOG, stmt));
        TaskOperation taskOp = new TaskOperation(new MSSQLTaskTable(LOG, stmt));

        register(new TelegramCommandAdapter(new StartCommand(userOp), LOG));
        register(new TelegramCommandAdapter(new PendingVerCommand(userOp), LOG));
        register(new TelegramCommandAdapter(new VerCommand(userOp), LOG));
        register(new TelegramCommandAdapter(new StopCommand(userOp), LOG));
        register(new TelegramCommandAdapter(new DelCommand(userOp), LOG));

        register(new TelegramCommandAdapter(new CreateTaskCommand(taskOp, userOp), LOG));
        register(new TelegramCommandAdapter(new GetTaskByNumberCommand(taskOp, userOp), LOG));
        register(new TelegramCommandAdapter(new ShowAllTasksCommand(taskOp, userOp), LOG));
        register(new TelegramCommandAdapter(new GetTaskByLevelCommand(taskOp, userOp), LOG));
        register(new TelegramCommandAdapter(new RemoveTaskCommand(taskOp, userOp), LOG));
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
        return;
//        if (!update.hasMessage()) {
//            throw new IllegalStateException("Update doesn't have a body!");
//        }
//
//        Message msg = update.getMessage();
//        LOG.log(Level.WARNING, msg.getText());
//        User user = msg.getFrom();
//        Chat chat = msg.getChat();
//
//        if (userIsNoVer(user, chat)){
//            return;
//        }
//
//        SendMessage answer = new SendMessage();
//        answer.setText(msg.getText());
//        answer.setChatId(verUsersGroup.getAdmin().getChat().getId());
//
//        try {
//            execute(answer);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
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
