package KernelBot;

import java.util.logging.Logger;

public class BotStarted {
    public static final Logger LOGS = Logger.getLogger(BotStarted.class.getName());

    public final String Platform;

    public BotStarted(String StartPaltform){
        Platform = StartPaltform;
        LOGS.info("Bot launch, platform:" + Platform);
        botIniterChange();
    }

    private void botIniterChange(){
        switch (Platform){
            case "CMD":
                initCMDBot();
                break;
        }

    }

    private static void initCMDBot(){

    }

}
