package TestUsersPackage;

import Users.UserBot;
import org.junit.Test;
import org.junit.Assert;

public class TestUserBot {

    @Test
    public void testGetters(){
        UserBot userBot = new UserBot("Jora", "12345678");

        Assert.assertEquals("Jora", userBot.getIdentifier());
        Assert.assertEquals("12345678", userBot.getChatId());
    }

}
