import Commands.*;
import DataBase.DataBaseMSSQL;
import DataBase.MSSQLTaskTable;
import DataBase.MSSQLUserTable;
import Tasks.TaskRepository;
import TelegramCommand.TelegramCommandAdapter;
import Users.UsersOperation;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;


public class RetraceBotMSG extends TelegramLongPollingCommandBot {
    private final Logger LOG;

    private static final String BOT_TOKEN = System.getenv("TOKEN");
    private static final String BOT_NAME = System.getenv("NAME_BOT");

    private LastUserQuery lastUserQuery;

    public RetraceBotMSG(Logger log) {
        LOG = log;

        lastUserQuery = new LastUserQuery();

        DataBaseMSSQL dataBaseMSSQL = new DataBaseMSSQL(LOG);
        Statement stmt = dataBaseMSSQL.connectDataBase();
        UsersOperation userOp = new UsersOperation(new MSSQLUserTable(LOG, stmt));
        TaskRepository taskOp = new TaskRepository(new MSSQLTaskTable(LOG, stmt));

        register(new TelegramCommandAdapter(new StartCommand(userOp), LOG));
        register(new TelegramCommandAdapter(new PendingVerCommand(userOp), LOG));
        register(new TelegramCommandAdapter(new VerCommand(userOp), LOG));
        register(new TelegramCommandAdapter(new StopCommand(userOp), LOG));
        register(new TelegramCommandAdapter(new DelCommand(userOp), LOG));
        register(new TelegramCommandAdapter(new HelpCommand(userOp), LOG));

        register(new TelegramCommandAdapter(new CreateTaskCommand(taskOp, userOp), LOG));
        register(new TelegramCommandAdapter(new RemoveTaskCommand(taskOp, userOp), LOG));

        register(new TelegramCommandAdapter(new GetTaskByNumberCommand(taskOp, userOp, lastUserQuery), LOG));
        register(new TelegramCommandAdapter(new ShowAllTasksCommand(taskOp, userOp, lastUserQuery), LOG));
        register(new TelegramCommandAdapter(new GetTaskByLevelCommand(taskOp, userOp, lastUserQuery), LOG));
        register(new TelegramCommandAdapter(new GetAnswerTaskCommand(userOp, taskOp, lastUserQuery), LOG));
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
        String[] strings = new String[0];
        if (!update.hasMessage()) {
            throw new IllegalStateException("Update doesn't have a body!");
        }

        Message msg = update.getMessage();
        Chat chat = msg.getChat();

        String command = lastUserQuery.getCommand(chat.getId().toString());

        String[] commandAndArgs = command.split("\\s+");
        for (String s: commandAndArgs) {
            System.out.println(command + "  CommandAndArgs  " + s);
        }

        IBotCommand iBotCommand = getRegisteredCommand(commandAndArgs[0]);

        try {
            strings = addArgsToCommand(msg.getText(), commandAndArgs[1]);
        } catch (ArrayIndexOutOfBoundsException ignore){
            strings = addArgsToCommand(msg.getText(), null);
        }

        iBotCommand.processMessage(this, msg, strings);
    }

    private String[] addArgsToCommand(String msgText, String argsCommand){
        ArrayList<String>  commandText= new ArrayList<String>(Arrays.asList(msgText.split("\\s+")));

        if (argsCommand != null){
            ArrayList<String> commandArgs = new ArrayList<String>(Arrays.asList(argsCommand.split("\\s+")));
            commandArgs.addAll(commandText);
            return commandArgs.toArray(new String[0]);
        } else {
            return commandText.toArray(new String[0]);
        }
    }

}
