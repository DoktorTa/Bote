package TestCommand;

import Command.MyNameCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;
import org.objenesis.instantiator.ObjectInstantiator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestMyNameCommand {
    public MyNameCommand myNameCom;

    @Before
    public void testSetUP(){

        Objenesis objenesis_my_name = new ObjenesisStd();
        ObjectInstantiator thingyInstantiator_my_name = objenesis_my_name.getInstantiatorOf(MyNameCommand.class);
        myNameCom = (MyNameCommand) thingyInstantiator_my_name.newInstance ();
    }

    @Test
    public void testGetTextNotRegUser() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = MyNameCommand.class.getDeclaredMethod("getTextNotRegUser");
        method.setAccessible(true);
        String output = (String) method.invoke(myNameCom);

        String answer = "You are not in bot users' list! Send /start command!";

        Assert.assertEquals(output, answer);
    }

    @Test
    public void testGetTextNotNameUser() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = MyNameCommand.class.getDeclaredMethod("getTextNotNameUser");
        method.setAccessible(true);
        String output = (String) method.invoke(myNameCom);

        String answer = "Currently you don't have a name.\nSet it using command:\n'/set_name &lt;displayed_name&gt;'";

        Assert.assertEquals(output, answer);
    }

    @Test
    public void testGetTextNameUser() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = MyNameCommand.class.getDeclaredMethod("getTextNameUser", String.class);
        method.setAccessible(true);
        String output = (String) method.invoke(myNameCom, "name");

        String answer = "Your current name: name";

        Assert.assertEquals(output, answer);
    }

}
