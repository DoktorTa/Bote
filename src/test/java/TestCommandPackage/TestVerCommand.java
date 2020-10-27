import Command.VerCommand;
import Users.NoVerUserBot;
import Users.UserBot;
import Users.VerUserBot;
import org.junit.Assert;
import org.junit.Test;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestVerCommand {

    @Test
    public void testDeleteNoVerUser() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        UserBot userBot = new UserBot(new User(123, "Peta", false, "Ivanov", "Jopa", "rus"), new Chat());
        VerUserBot verUserGroup = new VerUserBot();
        NoVerUserBot noVerUserGroup = new NoVerUserBot();
        VerCommand verCom = new VerCommand(verUserGroup, noVerUserGroup);

        Method method = VerCommand.class.getDeclaredMethod("deleteNoVerUser", String.class);
        method.setAccessible(true);
        UserBot outputNull = (UserBot) method.invoke(verCom, "PetaJopa");

        noVerUserGroup.addUserBot(userBot);

        UserBot output = (UserBot) method.invoke(verCom, "PetaJopa");

        Assert.assertNull(outputNull);
        Assert.assertEquals(output, userBot);
    }

    @Test
    public void testVerificationUser() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        UserBot userBot = new UserBot(new User(123, "Peta", false, "Ivanov", "Jopa", "rus"), new Chat());
        VerUserBot verUserGroup = new VerUserBot();
        NoVerUserBot noVerUserGroup = new NoVerUserBot();
        VerCommand verCom = new VerCommand(verUserGroup, noVerUserGroup);

        Method method = VerCommand.class.getDeclaredMethod("verificationUser", UserBot.class);
        method.setAccessible(true);
        String output = (String) method.invoke(verCom, userBot);

        Assert.assertEquals("User PetaJopa verification!", output);
    }
}
