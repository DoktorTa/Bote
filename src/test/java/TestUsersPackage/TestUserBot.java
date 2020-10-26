package TestUsersPackage;

import Users.UserBot;
import org.junit.Test;
import org.junit.Assert;
import org.telegram.telegrambots.meta.api.objects.User;


public class TestUserBot {

    @Test
    public void testSetIdentifierUser(){
        User user = new User(123, "Peta", false, "Ivanov", "Jopa", "rus");
        UserBot userBot = new UserBot(user);

        Assert.assertEquals(userBot.identifier, "PetaJopa");
    }

    @Test
    public void testEquals(){
        User userPeta =
                new User(123, "Peta", false, "Ivanov", "Jopa", "rus");
        User userVova =
                new User(456, "Vova", false, "Petrov", "Horosho", "rus");
        UserBot userBotPeta = new UserBot(userPeta);
        UserBot userBotVova = new UserBot(userVova);

        Assert.assertEquals(userBotPeta, userBotPeta);
        Assert.assertNotEquals(userBotPeta, userBotVova);
    }

}
