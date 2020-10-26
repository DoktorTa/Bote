import Command.PendingVerCommand;
import Command.StartCommand;
import Users.NoVerUserBot;
import Users.UserBot;
import Users.VerUserBot;
import org.junit.Assert;
import org.junit.Test;
import org.telegram.telegrambots.meta.api.objects.User;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestPendingVerCommand {

    @Test
    public void testUserIsAdmin() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        User user1 = new User(123, "Peta", false, "Ivanov", "Jopa", "rus");
        User user2 = new User(123, "Vova", false, "Ivanov", "MAGU", "rus");
        UserBot userBot = new UserBot(user1);
        VerUserBot verUsersGroup = new VerUserBot();
        NoVerUserBot noVerUsersGroup = new NoVerUserBot();
        PendingVerCommand pendingCom = new PendingVerCommand(verUsersGroup, noVerUsersGroup);

        verUsersGroup.setAdmin(userBot);

        Method method = PendingVerCommand.class.getDeclaredMethod("userIsAdmin", User.class);
        method.setAccessible(true);
        Boolean outputGood = (Boolean) method.invoke(pendingCom, user1);

        Boolean outputBad = (Boolean) method.invoke(pendingCom, user2);

        Assert.assertTrue(outputGood);
        Assert.assertFalse(outputBad);
    }

    @Test
    public void testGetBeadrollNoVerUser() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        UserBot user1 = new UserBot(new User(123, "Peta", false, "Ivanov", "Jopa", "rus"));
        UserBot user2 = new UserBot(new User(123, "Vova", false, "Ivanov", "MAGU", "rus"));
        VerUserBot verUsersGroup = new VerUserBot();
        NoVerUserBot noVerUsersGroup = new NoVerUserBot();
        PendingVerCommand pendingCom = new PendingVerCommand(verUsersGroup, noVerUsersGroup);
        String answer = "[0]|PetaJopa|Jopa\n[1]|VovaMAGU|MAGU\n";

        noVerUsersGroup.addUserBot(user1);
        noVerUsersGroup.addUserBot(user2);

        Method method = PendingVerCommand.class.getDeclaredMethod("getBeadrollNoVerUser");
        method.setAccessible(true);
        String outputGood = (String) method.invoke(pendingCom);

        Assert.assertEquals(answer, outputGood);
    }
}
