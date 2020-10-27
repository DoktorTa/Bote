import Users.NoVerUserBot;
import Users.UserBot;
import Users.UsersGroups;
import org.junit.Assert;
import org.junit.Test;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.ArrayList;

public class TestNoVerUserBot {

    @Test
    public void testAddUserBot() {
        ArrayList<UserBot> users = new ArrayList<>();
        while (users.size() < 11){
            UserBot userBot = new UserBot(new User(), new Chat());
            users.add(userBot);
        }

        UsersGroups groupNoVer = new NoVerUserBot();
        for (UserBot user: users) {
            Assert.assertTrue(groupNoVer.addUserBot(user));
        }

        // Assert.assertEquals(groupNoVer.usersGroup.size(), 10);
    }

    @Test
    public void testRemoveUserBot() {
        User user = new User(123, "Peta", false, "Ivanov", "Jopa", "rus");
        UserBot userBot = new UserBot(user, new Chat());
        UsersGroups groupNoVer = new NoVerUserBot();

        groupNoVer.addUserBot(userBot);

        Assert.assertTrue(groupNoVer.removeUserBot(user));
        Assert.assertFalse(groupNoVer.removeUserBot(user));
    }

    @Test
    public void testSearchUserBot(){
        UserBot user1 = new UserBot(new User(123, "Peta", false, "Ivanov", "Jopa", "rus"), new Chat());
        NoVerUserBot noVerUserGroup = new NoVerUserBot();

        noVerUserGroup.addUserBot(user1);

        Assert.assertEquals(user1, noVerUserGroup.searchUserBot("PetaJopa"));
        Assert.assertNull(noVerUserGroup.searchUserBot("VovaMAGU"));
    }

    @Test
    public void testGetUsersGroupString(){
        UserBot user1 = new UserBot(new User(123, "Peta", false, "Ivanov", "Jopa", "rus"), new Chat());
        NoVerUserBot noVerUserGroup = new NoVerUserBot();

        noVerUserGroup.addUserBot(user1);

        Assert.assertEquals("[0]|PetaJopa|Jopa\n", noVerUserGroup.getUsersGroupString());
    }
}
