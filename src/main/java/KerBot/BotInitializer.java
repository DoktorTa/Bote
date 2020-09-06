package KerBot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.logging.LogManager;
import java.util.logging.Level;
import java.util.logging.Logger;


public final class BotInitializer {

    private static final Logger LOG = LogManager.getLogManager().getLogger(BotInitializer.class.getName());

    private static final String PROXY_HOST = "xx.xx.xxx.xxx";
    private static final int PROXY_PORT = 9999;

    public static void main(String[] args) {

        try {
            runBot();
        } catch (TelegramApiRequestException e) {
            LOG.log(Level.SEVERE, "Error while initializing bot!", e);
        }
    }

    private static void runBot() throws TelegramApiRequestException {
        LOG.info("Initializer...");
        ApiContextInitializer.init();

        LOG.info("Set options...");
        DefaultBotOptions botOptions = setBotOptions();

        LOG.info("Registering...");
        TelegramBotsApi botsApi = runRegisterBot(botOptions);

        LOG.info("Start is good!");

    }

    private static TelegramBotsApi runRegisterBot(DefaultBotOptions botOptions){
        TelegramBotsApi botsApi = new TelegramBotsApi();
        return botsApi.registerBot(new AnonymizerBot(botOptions));
    }

    private static DefaultBotOptions setBotOptions(){
        DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);

        botOptions.setProxyHost(PROXY_HOST);
        botOptions.setProxyPort(PROXY_PORT);
        botOptions.setProxyType(DefaultBotOptions.ProxyType.SOCKS4);

        return botOptions;
    }
}
