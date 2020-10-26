import org.junit.Assert;
import org.junit.Test;
import org.telegram.telegrambots.meta.api.objects.User;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestStartCommand {

    @Test
    public void testCreateAdmin() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        User user = new User();
        VerUserBot verUsergroup = new VerUserBot();
        StartCommand startCom = new StartCommand(verUsergroup);

        Method method = StartCommand.class.getDeclaredMethod("createAdmin", User.class);
        method.setAccessible(true);
        String output = (String) method.invoke(startCom, user);

        Assert.assertEquals(output, "Hello my admin!");
    }

}
