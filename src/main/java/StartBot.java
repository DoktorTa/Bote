import DataBase.DataBaseMSSQL;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.SQLException;
import java.util.logging.Logger;

public class StartBot {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        greeting();
        startInitBot();
    }

    private static void greeting(){
        String helloString = "Hi admin, welcome to RetraceBotMSG\n" +
                "version 0, enter the name you will set for the bot administrator in Telegram: ";
        System.out.println(helloString);
    }

    /**
     * Инициализация запуска бота.
     */
    private static void startInitBot(){
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new RetraceBotMSG(Logger.getLogger(StartBot.class.getName())));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
