package TestCommand;

import Command.ComStart;
import Command.TelegramCommands;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.TestName;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestTelegramCommand {

    @Test
    public void testInitCommands() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        TelegramCommands testTelegramCommands = new TelegramCommands();

        Object objCommand = testTelegramCommands.Commands.get("/start");
        Assert.assertEquals(objCommand, new ComStart());
    }

    @Test
    public void testExecuteCommandError(){
        TelegramCommands testTelegramCommands = new TelegramCommands();

        Assert.assertEquals(
                testTelegramCommands.executeCommand("/herabora", ""), "Command not fond");
    }


    @Test
    public void testExecuteCommandNoError(){
        TelegramCommands testTelegramCommands = new TelegramCommands();

        Assert.assertEquals(
                testTelegramCommands.executeCommand("/bro", ""), "Bro");
    }
}
