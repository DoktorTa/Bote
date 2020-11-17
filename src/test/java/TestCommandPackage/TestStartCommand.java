package TestCommandPackage;

import Commands.StartCommand;
import Users.UserBot;
import Users.UsersOperation;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class TestStartCommand {

    @Test
    public void testGetAnswer(){
        String[] strings = new String[0];

        UserBot admin = new UserBot("TestAdmin", "00000000");
        UserBot user1 = new UserBot("TestUser1", "00000001");

        UsersOperation usersOperation = Mockito.mock(UsersOperation.class);
        Mockito.when(usersOperation.getAdminIdentifier()).thenReturn(null);
//        Mockito.when(usersOperation.getUserFromVerifiedUsers("TestUser1")).thenReturn(new ArrayList<>());
//        Mockito.when(usersOperation.addUserToNoVerifiedUsers(user1));
        StartCommand startCom = new StartCommand(usersOperation);

        Assert.assertEquals("Hello my admin!", startCom.getAnswer(admin, strings));
//        Assert.assertEquals("Welcome, await administrator verification.", startCom.getAnswer(user1, strings));
//        Assert.assertEquals("You are verified", );

    }

}
