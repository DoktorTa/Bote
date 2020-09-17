package KerBot;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class BotStarter {

    private static final Logger Logs = Logger.getLogger(BotStarter.class.getName());
    private static ArrayList<String> BotPlatforms = new ArrayList<String>();

    public BotStarter(){
        Logs.info("Start loader.");
        platformInit();
        try {
            changeBotPlatform();
        }
        catch (ArrayIndexOutOfBoundsException e){
            Logs.info("Incorrect bot");
        }
    }

    private static void platformInit(){
        // При любом условии телеграмм должен идти первым.
        BotPlatforms.add("Telegram");
    }

    private static String changeBotPlatform() throws ArrayIndexOutOfBoundsException{
        printVariant();
        int numChange = changeVariant();
        return BotPlatforms.get(numChange);
    }

    private static void printVariant(){
        String separator = "| ";

        for (String platform : BotPlatforms){
            int numPlatformInArray = BotPlatforms.indexOf(platform);
            String linePlatform = Integer.toString(numPlatformInArray) + separator + platform;
            System.out.println(linePlatform);
            Logs.info(linePlatform);
        }
    }

    private static int changeVariant(){
        Scanner in = new Scanner(System.in);
        System.out.println("Input a number row starting platform: ");
        try {
            int num = in.nextInt();
            return num;
        }
        catch (ClassCastException e){
            Logs.info("No num.");
            return 0;
        }
    }
}
