import org.junit.Assert;
import org.junit.Test;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.ArrayList;

public class TestNoVerUserBot {

    @Test
    public void testAddUserBot() {
        ArrayList<UserBot> users = new ArrayList<UserBot>();
        while (users.size() < 11){
            User user = new User();
            UserBot userBot = new UserBot(user);
            users.add(userBot);
        }

        UsersGruops groupNoVer = new NoVerUserBot();
        for (UserBot user: users) {
            Assert.assertTrue(groupNoVer.addUserBot(user));
        }

        Assert.assertEquals(groupNoVer.usersGroup.size(), 10);
    }

    @Test
    public void testRemoveUserBot() {
        User user = new User(123, "Peta", false, "Ivanov", "Jopa", "rus");
        UserBot userBot = new UserBot(user);
        UsersGruops groupNoVer = new NoVerUserBot();

        groupNoVer.addUserBot(userBot);

        Assert.assertTrue(groupNoVer.removeUserBot(user));
        Assert.assertFalse(groupNoVer.removeUserBot(user));
    }
}
