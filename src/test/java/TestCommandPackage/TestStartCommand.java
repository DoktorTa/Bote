import Command.StartCommand;
import Users.NoVerUserBot;
import Users.UserBot;
import Users.VerUserBot;
import org.junit.Assert;
import org.junit.Test;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestStartCommand {

    @Test
    public void testCreateAdmin() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        User user = new User();
        Chat chat = new Chat();
        VerUserBot verUserGroup = new VerUserBot();
        NoVerUserBot noVerUserGroup = new NoVerUserBot();
        StartCommand startCom = new StartCommand(verUserGroup, noVerUserGroup);

        Method method = StartCommand.class.getDeclaredMethod("createAdmin", User.class, Chat.class);
        method.setAccessible(true);
        String output = (String) method.invoke(startCom, user, chat);

        Assert.assertEquals(output, "Hello my admin!");
    }

    @Test
    public void testAddNoVerUser() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        User user = new User(123, "Peta", false, "Ivanov", "Jopa", "rus");
        Chat chat = new Chat();
        VerUserBot verUserGroup = new VerUserBot();
        NoVerUserBot noVerUserGroup = new NoVerUserBot();
        StartCommand startCom = new StartCommand(verUserGroup, noVerUserGroup);

        Method methodGood = StartCommand.class.getDeclaredMethod("addNoVerUser", User.class, Chat.class);
        methodGood.setAccessible(true);
        String outputGood = (String) methodGood.invoke(startCom, user, chat);

        Assert.assertEquals("Welcome, await administrator verification", outputGood);
        Assert.assertTrue(noVerUserGroup.userInGroup(new UserBot(user, new Chat())));
    }

    @Test
    public void testAdminExistence() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        User user = new User();
        Chat chat = new Chat();
        VerUserBot verUserGroup = new VerUserBot();
        NoVerUserBot noVerUserGroup = new NoVerUserBot();
        StartCommand startCom = new StartCommand(verUserGroup, noVerUserGroup);

        Method method = StartCommand.class.getDeclaredMethod("adminExistence");
        method.setAccessible(true);
        Boolean outputTrue = (Boolean) method.invoke(startCom);

        Method methodCreateAdmin = StartCommand.class.getDeclaredMethod("createAdmin", User.class, Chat.class);
        methodCreateAdmin.setAccessible(true);
        String output = (String) methodCreateAdmin.invoke(startCom, user, chat);

        Boolean outputFalse = (Boolean) method.invoke(startCom);

        Assert.assertFalse(outputFalse);
        Assert.assertTrue(outputTrue);

    }
}
