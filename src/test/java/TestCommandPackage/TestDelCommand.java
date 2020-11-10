//import Command.DelCommand;
//import Users.NoVerUserBot;
//import Users.UserBot;
//import Users.VerUserBot;
//import org.junit.Assert;
//import org.junit.Test;
//import org.telegram.telegrambots.meta.api.objects.Chat;
//import org.telegram.telegrambots.meta.api.objects.User;
//
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//
//public class TestDelCommand {
//
//    @Test
//    public void testDelInNoVerGroup() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        UserBot userBot = new UserBot(new User(123, "Peta", false, "Ivanov", "Jopa", "rus"), new Chat());
//        VerUserBot verUserGroup = new VerUserBot();
//        NoVerUserBot noVerUserGroup = new NoVerUserBot();
//        DelCommand delCom = new DelCommand(verUserGroup, noVerUserGroup);
//
//        Method method = DelCommand.class.getDeclaredMethod("delInNoVerGroup", String.class);
//        method.setAccessible(true);
//        String outputBad = (String) method.invoke(delCom, "PetaJopa");
//
//        noVerUserGroup.addUserBot(userBot);
//
//        String outputGood = (String) method.invoke(delCom, "PetaJopa");
//
//        Assert.assertEquals("User PetaJopa deleted!", outputGood);
//        Assert.assertEquals("User PetaJopa not found in not verification list!", outputBad);
//    }
//}
