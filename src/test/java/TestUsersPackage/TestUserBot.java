//import Users.UserBot;
//import org.junit.Test;
//import org.junit.Assert;
//import org.telegram.telegrambots.meta.api.objects.Chat;
//import org.telegram.telegrambots.meta.api.objects.User;
//
//
//public class TestUserBot {
//
//    @Test
//    public void testSetIdentifierUser(){
//        User user = new User(123, "Peta", false, "Ivanov", "Jopa", "rus");
//        UserBot userBot = new UserBot(user, new Chat());
//
//        Assert.assertEquals(userBot.identifier, "PetaJopa");
//    }
//
//    @Test
//    public void testEquals(){
//        User userPeta =
//                new User(123, "Peta", false, "Ivanov", "Jopa", "rus");
//        User userVova =
//                new User(456, "Vova", false, "Petrov", "Horosho", "rus");
//        UserBot userBotPeta = new UserBot(userPeta, new Chat());
//        UserBot userBotVova = new UserBot(userVova, new Chat());
//
//        Assert.assertEquals(userBotPeta, userBotPeta);
//        Assert.assertNotEquals(userBotPeta, userBotVova);
//    }
//
//    @Test
//    public void testGetters(){
//        User user = new User();
//        Chat chat = new Chat();
//        UserBot userBot = new UserBot(user, chat);
//
//        Assert.assertEquals(user, userBot.getUser());
//        Assert.assertEquals(chat, userBot.getChat());
//    }
//
//}
