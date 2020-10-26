import Command.StartCommand;
import Users.NoVerUserBot;
import Users.UserBot;
import Users.VerUserBot;
import org.junit.Assert;
import org.junit.Test;
import org.telegram.telegrambots.meta.api.objects.User;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestStartCommand {

    @Test
    public void testCreateAdmin() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        User user = new User();
        VerUserBot verUserGroup = new VerUserBot();
        NoVerUserBot noVerUserGroup = new NoVerUserBot();
        StartCommand startCom = new StartCommand(verUserGroup, noVerUserGroup);

        Method method = StartCommand.class.getDeclaredMethod("createAdmin", User.class);
        method.setAccessible(true);
        String output = (String) method.invoke(startCom, user);

        Assert.assertEquals(output, "Hello my admin!");
    }

    @Test
    public void testAddNoVerUser() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        User user = new User(123, "Peta", false, "Ivanov", "Jopa", "rus");
        VerUserBot verUserGroup = new VerUserBot();
        NoVerUserBot noVerUserGroup = new NoVerUserBot();
        StartCommand startCom = new StartCommand(verUserGroup, noVerUserGroup);

        Method methodGood = StartCommand.class.getDeclaredMethod("addNoVerUser", User.class);
        methodGood.setAccessible(true);
        String outputGood = (String) methodGood.invoke(startCom, user);

        Assert.assertEquals("Welcome, await administrator verification", outputGood);
        Assert.assertTrue(noVerUserGroup.userInGroup(new UserBot(user)));
    }
}
