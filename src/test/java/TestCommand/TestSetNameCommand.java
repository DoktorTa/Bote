package TestCommand;

import Command.SetNameCommand;
import Command.StopCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;
import org.objenesis.instantiator.ObjectInstantiator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestSetNameCommand {
    public SetNameCommand setNameCom;

    @Before
    public void setUpPreTest(){
        Objenesis objenesisSetName = new ObjenesisStd();
        ObjectInstantiator thingyInstantiatorSetName = objenesisSetName.getInstantiatorOf(SetNameCommand.class);
        setNameCom = (SetNameCommand) thingyInstantiatorSetName.newInstance ();
    }

    @Test
    public void testGetTextNoActiveStartCom() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method method = SetNameCommand.class.getDeclaredMethod("getTextNoActiveStartCom");
        method.setAccessible(true);
        String output = (String) method.invoke(setNameCom);

        String textMsg = "Firstly you should start the bot! Execute '/start' command!";

        Assert.assertEquals(output, textMsg);
    }

    @Test
    public void testGetTextNullName() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException{
        Method method = SetNameCommand.class.getDeclaredMethod("getTextNullName");
        method.setAccessible(true);
        String output = (String) method.invoke(setNameCom);

        String textMsg = "You should use non-empty name!";

        Assert.assertEquals(output, textMsg);
    }

    @Test
    public void testGetTextSetName() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException{
        Method method = SetNameCommand.class.getDeclaredMethod("getTextSetName", String.class);
        method.setAccessible(true);
        String output = (String) method.invoke(setNameCom, "123");

        String textMsg = "Your displayed name: '" + "123" + "'. Now you can send messages to bot!";

        Assert.assertEquals(output, textMsg);
    }

    @Test
    public void testGetTextChangeName() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException{
        Method method = SetNameCommand.class.getDeclaredMethod("getTextChangeName", String.class);
        method.setAccessible(true);
        String output = (String) method.invoke(setNameCom, "123");

        String textMsg = "Your new displayed name: '" + "123" + "'.";

        Assert.assertEquals(output, textMsg);
    }

    @Test
    public void testGetTextNameAlreadyUse() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException{
        Method method = SetNameCommand.class.getDeclaredMethod("getTextNameAlreadyUse", String.class);
        method.setAccessible(true);
        String output = (String) method.invoke(setNameCom, "123");

        String textMsg = "Name " + "123" + " is already in use! Choose another name!";

        Assert.assertEquals(output, textMsg);
    }
}
