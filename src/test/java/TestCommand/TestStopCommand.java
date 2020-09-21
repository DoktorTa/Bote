package TestCommand;

import Command.StopCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;
import org.objenesis.instantiator.ObjectInstantiator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestStopCommand {
    public StopCommand stopCom;

    @Before
    public void setUpPreTest(){
        Objenesis objenesisStop = new ObjenesisStd();
        ObjectInstantiator thingyInstantiatorStop = objenesisStop.getInstantiatorOf(StopCommand.class);
        stopCom = (StopCommand) thingyInstantiatorStop.newInstance ();
    }

    @Test
    public void testStopCommandGoodMsg() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = StopCommand.class.getDeclaredMethod("setGoodAnswer", String.class);
        method.setAccessible(true);
        String output = (String) method.invoke(stopCom, "user");

        String answer = "user you've been removed from bot's users list! Bye!";

        Assert.assertEquals(output, answer);
    }


    @Test
    public void testStopCommandBadMsg() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = StopCommand.class.getDeclaredMethod("setBadAnswer");
        method.setAccessible(true);
        String output = (String) method.invoke(stopCom);

        String answer = "You were not in bot users' list. Bye!";

        Assert.assertEquals(output, answer);
    }
}
