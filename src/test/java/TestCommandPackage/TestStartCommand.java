package TestCommandPackage;

import Commands.Standart.StartCommand;
import Users.UserBot;
import Users.UsersRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class TestStartCommand {

    @Test
    public void testGetAnswer(){
        String[] strings = new String[0];

        UserBot admin = new UserBot("TestAdmin", "00000000");
        UserBot user1 = new UserBot("TestUser1", "00000001");

        UsersRepository usersOperation = Mockito.mock(UsersRepository.class);
        Mockito.when(usersOperation.getAdminIdentifier()).thenReturn(null);
//        Mockito.when(usersOperation.getUserFromVerifiedUsers("TestUser1")).thenReturn(new ArrayList<>());
//        Mockito.when(usersOperation.addUserToNoVerifiedUsers(user1));
        StartCommand startCom = new StartCommand(usersOperation);

        Assert.assertEquals("Hello my admin!", startCom.getAnswer(admin, strings));
//        Assert.assertEquals("Welcome, await administrator verification.", startCom.getAnswer(user1, strings));
//        Assert.assertEquals("You are verified", );

    }

}
