package TestCommandPackage;

import Commands.StartCommand;
import DataBase.MSSQLTaskTable;
import Users.UserBot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestStartCommand {

//    @Test
//    public void testGetAnswer() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        User user1 = new User(123, "Peta", false, "Ivanov", "Jopa", "rus");
//        User user2 = new User(123, "Vova", false, "Ivanov", "MAGU", "rus");
//        Chat chat = new Chat();
//        StartCommand startCom = new StartCommand(verUserGroup, noVerUserGroup);
//
//
//        MSSQLTaskTable mssqlTaskTable = Mockito.mock(MSSQLTaskTable.class);
//        Mockito.when(mssqlTaskTable.addTask("1", "1", "Text", "Answer", "Good")).thenReturn(true);
//
//
//        Method method = StartCommand.class.getDeclaredMethod("getTextMSG", User.class, Chat.class);
//        method.setAccessible(true);
//
//        String outputHelloAdmin = (String) method.invoke(startCom, user1, chat);
//        String outputWelcome = (String) method.invoke(startCom, user2, chat);
//
////        verUserGroup.addUserBot()
//
//        Assert.assertEquals("Hello my admin!", outputHelloAdmin);
//        Assert.assertEquals("Welcome, await administrator verification.", outputWelcome);
////        Assert.assertEquals("You are verified", );
//
//    }

}
