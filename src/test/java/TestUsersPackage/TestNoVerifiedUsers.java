package TestUsersPackage;

import Users.NoVerifiedUsers;
import Users.UserBot;
import Users.UsersGroups;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TestNoVerifiedUsers {

    @Test
    public void testAddUserBot() {
        ArrayList<UserBot> users = new ArrayList<>();
        Integer id = 0;

        while (users.size() < 11){
            id++;
            UserBot userBot = new UserBot("Peta_Jora", id.toString());
            users.add(userBot);
        }

        UsersGroups groupNoVer = new NoVerifiedUsers();
        for (UserBot user: users) {
            Assert.assertTrue(groupNoVer.addUserBot(user));
        }

//         Assert.assertEquals(groupNoVer.usersGroup.size(), 10);
    }

    @Test
    public void testRemoveUserBot() {
        UserBot userBot = new UserBot("Peta_Jora", "00000000");
        UsersGroups groupNoVer = new NoVerifiedUsers();

        groupNoVer.addUserBot(userBot);

        Assert.assertTrue(groupNoVer.removeUserBot("Peta_Jora"));
        Assert.assertFalse(groupNoVer.removeUserBot("Peta_Jora"));
    }

    @Test
    public void testSearchUserBot(){
        UserBot user1 = new UserBot("Peta_Jora", "00000000");
        NoVerifiedUsers noVerUserGroup = new NoVerifiedUsers();

        noVerUserGroup.addUserBot(user1);

        Assert.assertEquals(user1, noVerUserGroup.searchUserBot("Peta_Jora"));
        Assert.assertNull(noVerUserGroup.searchUserBot("VovaMAGU"));
    }

    @Test
    public void testGetUsersGroupString(){
        UserBot user1 = new UserBot("Peta_Jora", "00000000");
        NoVerifiedUsers noVerUserGroup = new NoVerifiedUsers();

        noVerUserGroup.addUserBot(user1);

        Assert.assertEquals("[0]|Peta_Jora|\n", noVerUserGroup.getUsersGroupString());
    }
}
