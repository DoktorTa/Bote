package TestUsersPackage;

import Users.UserBot;
import Users.UsersGruops;
import Users.VerUserBot;
import org.junit.Assert;
import org.junit.Test;
import org.telegram.telegrambots.meta.api.objects.User;

public class TestVerUserBot {

    @Test
    public void testSetAdminGetAdmin(){
        User user = new User();
        UserBot userBot = new UserBot(user);
        VerUserBot groupNoVer = new VerUserBot();

        groupNoVer.setAdmin(userBot);

        Assert.assertEquals(groupNoVer.getAdmin(), userBot);
    }

    @Test
    public void testAddUserBot() {
        User user = new User();
        UserBot userBot = new UserBot(user);
        VerUserBot groupNoVer = new VerUserBot();


        Assert.assertTrue(groupNoVer.addUserBot(userBot));
        Assert.assertFalse(groupNoVer.addUserBot(userBot));
    }

    @Test
    public void testRemoveUserBot() {
        User user = new User(123, "Peta", false, "Ivanov", "Jopa", "rus");
        UserBot userBot = new UserBot(user);
        UsersGruops groupNoVer = new VerUserBot();

        groupNoVer.addUserBot(userBot);

        Assert.assertTrue(groupNoVer.removeUserBot(user));
        Assert.assertFalse(groupNoVer.removeUserBot(user));
    }

    @Test
    public void testUserInGroup(){
        User user = new User();
        UserBot userBot = new UserBot(user);
        VerUserBot groupNoVer = new VerUserBot();

        Assert.assertFalse(groupNoVer.userInGroup(userBot));
        groupNoVer.addUserBot(userBot);
        Assert.assertTrue(groupNoVer.userInGroup(userBot));
    }
}
