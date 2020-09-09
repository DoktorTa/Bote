package TestKerBot;

import KerBot.BotStarter;
import org.junit.Test;
import org.junit.Assert;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestBotStarter {

    @Test
    void testChangeBotPlatform() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        BotStarter bot = new BotStarter();
        String changedPlatform = "Telegram";

        Method method = BotStarter.class.getDeclaredMethod("changeBotPlatform", String.class);
        method.setAccessible(true);
        String output = (String) method.invoke(bot);

        Assert.assertEquals(output, changedPlatform);

    }
}
