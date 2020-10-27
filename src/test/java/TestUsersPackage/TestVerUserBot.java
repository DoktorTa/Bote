import Users.UserBot;
import Users.UsersGroups;
import Users.VerUserBot;
import org.junit.Assert;
import org.junit.Test;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;

public class TestVerUserBot {

    @Test
    public void testSetAdminGetAdmin(){
        UserBot userBot = new UserBot(new User(), new Chat());
        VerUserBot groupNoVer = new VerUserBot();

        groupNoVer.setAdmin(userBot);

        Assert.assertEquals(groupNoVer.getAdmin(), userBot);
    }

    @Test
    public void testAddUserBot() {
        UserBot userBot = new UserBot(new User(), new Chat());
        VerUserBot groupNoVer = new VerUserBot();


        Assert.assertTrue(groupNoVer.addUserBot(userBot));
        Assert.assertFalse(groupNoVer.addUserBot(userBot));
    }

    @Test
    public void testRemoveUserBot() {
        User user = new User(123, "Peta", false, "Ivanov", "Jopa", "rus");
        UserBot userBot = new UserBot(user, new Chat());
        UsersGroups groupNoVer = new VerUserBot();

        groupNoVer.addUserBot(userBot);

        Assert.assertTrue(groupNoVer.removeUserBot(user));
        Assert.assertFalse(groupNoVer.removeUserBot(user));
    }

    @Test
    public void testUserInGroup(){
        UserBot userBot = new UserBot(new User(), new Chat());
        VerUserBot groupNoVer = new VerUserBot();

        Assert.assertFalse(groupNoVer.userInGroup(userBot));
        groupNoVer.addUserBot(userBot);
        Assert.assertTrue(groupNoVer.userInGroup(userBot));
    }
}
