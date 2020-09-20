package TestCommand;

import Command.StartCommand;
import Command.StopCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;
import org.objenesis.instantiator.ObjectInstantiator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestStandardTextCommand{
    public StartCommand startCom;
    public StopCommand stopCom;

    @Before
    public void testSetUP(){
        Objenesis objenesis_start = new ObjenesisStd();
        ObjectInstantiator thingyInstantiator_start = objenesis_start.getInstantiatorOf(StartCommand.class);
        startCom = (StartCommand) thingyInstantiator_start.newInstance ();

        Objenesis objenesis_stop = new ObjenesisStd();
        ObjectInstantiator thingyInstantiator_stop = objenesis_stop.getInstantiatorOf(StopCommand.class);
        stopCom = (StopCommand) thingyInstantiator_stop.newInstance ();
    }

    @Test
    public void testStartCommandGoodMsg() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method method = StartCommand.class.getDeclaredMethod("setGoodAnswer", String.class);
        method.setAccessible(true);
        String output = (String) method.invoke(startCom, "user");

        String answer = "Hi, user! You've been added to bot users' list!\n" +
                "Please execute command:\n" +
                "'/set_name <displayed_name>'\n" +
                "where &lt;displayed_name&gt; is the name you want to use to hide your real name.";

        Assert.assertEquals(output, answer);

    }


    @Test
    public void TestStartCommandbadMsg() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = StartCommand.class.getDeclaredMethod("setBadAnswer");
        method.setAccessible(true);
        String output = (String) method.invoke(startCom);

        String answer = "You've already started bot! You can send messages if you set your name (/set_name).";

        Assert.assertEquals(output, answer);
    }


    @Test
    public void TestStopCommandGoodMsg() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = StopCommand.class.getDeclaredMethod("setGoodAnswer", String.class);
        method.setAccessible(true);
        String output = (String) method.invoke(stopCom, "user");

        String answer = "user you've been removed from bot's users list! Bye!";

        Assert.assertEquals(output, answer);
    }


    @Test
    public void TestStopCommandBadMsg() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = StopCommand.class.getDeclaredMethod("setBadAnswer");
        method.setAccessible(true);
        String output = (String) method.invoke(stopCom);

        String answer = "You were not in bot users' list. Bye!";

        Assert.assertEquals(output, answer);
    }
}
