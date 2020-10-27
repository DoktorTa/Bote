import Command.StopCommand;
import Users.NoVerUserBot;
import Users.UserBot;
import Users.VerUserBot;
import org.junit.Assert;
import org.junit.Test;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestStopCommand {

    @Test
    public void testDelInVerGroup() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        User user = new User(123, "Peta", false, "Ivanov", "Jopa", "rus");
        UserBot userBot = new UserBot(user, new Chat());
        VerUserBot verUserGroup = new VerUserBot();
        NoVerUserBot noVerUserGroup = new NoVerUserBot();
        StopCommand stopCom = new StopCommand(verUserGroup, noVerUserGroup);

        Method method = StopCommand.class.getDeclaredMethod("delInVerGroup", User.class);
        method.setAccessible(true);
        String outputStop = (String) method.invoke(stopCom, user);

        Assert.assertEquals("STOP", outputStop);
        Assert.assertFalse(verUserGroup.userInGroup(userBot));
    }
}
