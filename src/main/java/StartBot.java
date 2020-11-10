import DataBase.DataBaseMSSQL;
import DataBase.IDataBase;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

public class StartBot {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        greeting();
        createDataBase();
    }

    private static void greeting(){
        String helloString = "Hi admin, welcome to RetraceBotMSG\n" +
                "version 0, enter the name you will set for the bot administrator in Telegram: ";
        System.out.println(helloString);
    }

    private static void createDataBase() throws SQLException, ClassNotFoundException {
        Logger LOG = Logger.getLogger(StartBot.class.getName());
        DataBaseMSSQL Botes = new DataBaseMSSQL(LOG);
        Botes.connectDataBase();

        System.out.println(Botes.getTaskByNumber("1"));

        ArrayList<String> resultSet = Botes.getTaskByLevel("1");

        for (String line: resultSet) {
            System.out.println(line);
        }

    }

    /**
     * Инициализация запуска бота.
     */
    private static void startInitBot(){
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new RetraceBotMSG());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
