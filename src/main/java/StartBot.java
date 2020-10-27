import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Scanner;

public class StartBot {

    public static void main(String args[]){
        greeting();
        String nameAdmin = "q";
        startInitBot(nameAdmin);
    }

    private static void greeting(){
        String helloString = "Hi admin, welcome to RetraceBotMSG\n" +
                "version 0, enter the name you will set for the bot administrator in Telegram: ";
        System.out.println(helloString);
    }

    private static String getAdminName(){
        Scanner in = new Scanner(System.in);
        return in.next();
    }

    private static void startInitBot(String nameAdmin){
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new RetraceBotMSG(nameAdmin));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
