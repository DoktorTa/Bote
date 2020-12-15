import Commands.*;
import Commands.Standart.HelpCommand;
import Commands.Standart.StartCommand;
import Commands.Standart.StopCommand;
import Commands.Task.*;
import DataBase.DataBaseMSSQL;
import DataBase.MSSQLTaskTable;
import DataBase.MSSQLUserTable;
import Tasks.TaskRepository;
import TelegramCommand.TelegramCommandAdapter;
import Users.IUserRepository;
import Users.UserRepository;
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
        IUserRepository userOp = new UserRepository(new MSSQLUserTable(LOG, stmt));
        TaskRepository taskOp = new TaskRepository(new MSSQLTaskTable(LOG, stmt));

        register(new TelegramCommandAdapter(new StartCommand(userOp, lastUserQuery), LOG));
        register(new TelegramCommandAdapter(new StopCommand(userOp, lastUserQuery), LOG));
        register(new TelegramCommandAdapter(new HelpCommand(userOp, lastUserQuery), LOG));

        register(new TelegramCommandAdapter(new GetTaskByNumberCommand(userOp, taskOp, lastUserQuery), LOG));
        register(new TelegramCommandAdapter(new ShowAllTasksCommand(userOp, taskOp, lastUserQuery), LOG));
        register(new TelegramCommandAdapter(new GetTaskByLevelCommand(userOp, taskOp, lastUserQuery), LOG));
        register(new TelegramCommandAdapter(new GetAnswerTaskCommand(userOp, taskOp, lastUserQuery), LOG));
        register(new TelegramCommandAdapter(new ChoiceLvlTaskCommand(userOp, taskOp, lastUserQuery), LOG));
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
        processCommand(update.getMessage());
    }

    private void processCommand(Message msg){
        Chat chat = msg.getChat();

        String textMsg = msg.getText().replace("/", "");

        String[] commandAndArgs = getCommandAndArgs(chat.getId().toString());
        IBotCommand iBotCommand = getRegisteredCommand(commandAndArgs[0]);
        String[] strings = createArgsCommand(textMsg, commandAndArgs);

        iBotCommand.processMessage(this, msg, strings);
    }

    private String[] getCommandAndArgs(String chatId){
        String command = lastUserQuery.getCommand(chatId);
        return command.split("\\s+");
    }

    private String[] createArgsCommand(String textMSG, String[] commandAndArgs){
        try {
            return addArgsToCommand(textMSG, commandAndArgs);
        } catch (ArrayIndexOutOfBoundsException ignore){
            return addArgsToCommand(textMSG, null);
        }
    }

    private String[] addArgsToCommand(String msgText, String[] argsCommand){
        ArrayList<String> commandText = new ArrayList<>(Arrays.asList(msgText.split("\\s+")));

        if ((argsCommand != null) && (argsCommand[1] != null)){
            ArrayList<String> commandArgs = new ArrayList<>(Arrays.asList(argsCommand[1].split("\\s+")));
            commandArgs.addAll(commandText);

            return commandArgs.toArray(new String[0]);
        } else {
            return commandText.toArray(new String[0]);
        }
    }

}
