package TestCommand;

import Command.StartCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;
import org.objenesis.instantiator.ObjectInstantiator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestStartCommand {
    public StartCommand startCom;

    @Before
    public void setUpPreTest(){
        Objenesis objenesisStart = new ObjenesisStd();
        ObjectInstantiator thingyInstantiatorStart = objenesisStart.getInstantiatorOf(StartCommand.class);
        startCom = (StartCommand)thingyInstantiatorStart.newInstance();
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
    public void testStartCommandbadMsg() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = StartCommand.class.getDeclaredMethod("setBadAnswer");
        method.setAccessible(true);
        String output = (String) method.invoke(startCom);

        String answer = "You've already started bot! You can send messages if you set your name (/set_name).";

        Assert.assertEquals(output, answer);
    }
}
