package KernelBot;

import org.glassfish.jersey.client.internal.routing.AbortedRequestMediaTypeDeterminer;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class botStart {

    private static final Logger LOG = Logger.getLogger(botStart.class.getName());
    private static final Level infoLog = Level.INFO;

    private static final String PROXY_HOST = "127.0.0.1";
    private static final int PROXY_PORT = 9150;

    public botStart() {

        try {
            botInitilize();
        } catch (TelegramApiRequestException e) {
            LOG.log(infoLog,"Error while initializing bot!" + e);
        }
    }

    private void botInitilize() throws TelegramApiRequestException {
        LOG.log(infoLog, "Initializing API context...");
        ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();
        DefaultBotOptions botOptions = setBotOptions();

        LOG.log(infoLog,"Registering Anonymizer...");
        botsApi.registerBot(new AnonymizerBot(botOptions));

        LOG.log(infoLog,"Anonymizer bot is ready for work!");

    }

    private DefaultBotOptions setBotOptions(){
        LOG.log(infoLog, "Configuring bot options...");
        DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);

        botOptions.setProxyHost(PROXY_HOST);
        botOptions.setProxyPort(PROXY_PORT);
        botOptions.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);

        return botOptions;

    }
}